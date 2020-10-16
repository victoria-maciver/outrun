package uk.co.outrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.outrun.model.Dog;
import uk.co.outrun.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

//    @Autowired
//    DogNumService numService;

    private List<Dog> convertToList(Iterable<Dog> iterable) {
        List<Dog> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public List<Dog> getAllDogs() {
        List<Dog> list = new ArrayList<>();
        dogRepository.findAll().forEach(list::add);
        return list;
    }
    
    @Override
    public Dog getDogById(int id) {
        Optional<Dog> dog = dogRepository.findById(id);
        return dog.orElse(null);
    }

        @Override
    public List<Dog> getFamilyTree(int id) {
        List<Dog> family = new ArrayList<>();
        Dog child = dogRepository.findById(id).orElse(null);
        family.add(child);

        if (child == null) {
            return null;
        }

        if (child.getSireId() != null) {
            family.add(dogRepository.findById(child.getSireId()).orElse(null));
        } else {
            family.add(null);
        }

        if (child.getDamId() != null) {
            family.add(dogRepository.findById(child.getDamId()).orElse(null));
        } else {
            family.add(null);
        }

        return family;
    }

    @Override
    public List<Dog> getChildren(int id) {
        return dogRepository.findChildren(id);
    }

    @Override
    public List<Dog> getSiblings(int dogNum) {
        Dog child = dogRepository.findById(dogNum).orElse(null);

        if (child == null) {
            return null;
        }
        // If one parent is unknown, then there is no way to know their siblings
        if (child.getSireId() != null && child.getDamId() != null) {
            return dogRepository.findSiblings(dogNum, child.getSireId(), child.getDamId());
        } else {
            return null;
        }
    }

}
