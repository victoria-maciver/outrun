package uk.co.outrun.service;

import org.springframework.stereotype.Service;
import uk.co.outrun.model.Dog;

import java.util.List;

@Service
public interface DogService {
    Iterable<Dog> getAllDogs();
    Dog getDogById(int id);

    List<Dog> getFamilyTree(int dogNum);

    List<Dog> getChildren(int dogNum);

    List<Dog> getSiblings(int dogNum);

}
