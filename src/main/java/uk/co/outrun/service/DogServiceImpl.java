package uk.co.outrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.outrun.model.Dog;
import uk.co.outrun.model.SearchRequest;
import uk.co.outrun.repository.DogNumRepository;
import uk.co.outrun.repository.DogRepository;

import java.util.List;
import java.util.Objects;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogNumRepository numRepository;

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


}
