package uk.co.outrun.service;

import org.springframework.stereotype.Service;
import uk.co.outrun.model.Dog;

import java.util.List;

@Service
public interface DogService {
    List<Dog> getAllDogs();

    Dog getDogById(int id);

    List<Dog> getFamilyTree(int id);

    List<Dog> getChildren(int id);

    List<Dog> getSiblings(int id);

}
