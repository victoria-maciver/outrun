package uk.co.outrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.outrun.model.Dog;
import uk.co.outrun.model.SearchRequest;
import uk.co.outrun.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogNumService numService;

    @Override
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @Override
    public List<Dog> getDogByRegName(String regName) {
        return dogRepository.findByRegName(regName);
    }

    public List<Dog> searchByRegName(String regName) {
        return dogRepository.searchByRegName(regName);
    }

    public List<Dog> search(SearchRequest req) {
        return dogRepository.customSearch(
                Objects.toString(req.getRegName(), ""),
                Objects.toString(req.getSex(), "")
        );
    }

    @Override
    public void newDog(Dog dog) {
        int next = numService.getNext();
        dog.setDogNum(next);
        dogRepository.save(dog);
    }

    @Override
    public Dog getDogByDogNum(int id) {
        return dogRepository.findByDogNum(id);
    }

    @Override
    public List<Dog> getFamilyTree(int dogNum) {
        List<Dog> family = new ArrayList<>();
        Dog child = dogRepository.findByDogNum(dogNum);
        family.add(child);

        if (child.getSireId() != null) {
            family.add(dogRepository.findByDogNum(child.getSireId()));
        } else {
            family.add(null);
        }

        if (child.getDamId() != null) {
            family.add(dogRepository.findByDogNum(child.getDamId()));
        } else {
            family.add(null);
        }

        return family;
    }

    @Override
    public List<Dog> getChildren(int dogNum) {
        return dogRepository.findChildren(dogNum);
    }

    @Override
    public List<Dog> getSiblings(int dogNum) {
        Dog child = dogRepository.findByDogNum(dogNum);
        // If one parent is unknown, then there is no way to know their siblings
        if (child.getSireId() != null && child.getDamId() != null) {
            return dogRepository.findSiblings(dogNum, child.getSireId(), child.getDamId());
        } else {
            return null;
        }
    }

}
