package uk.co.outrun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.outrun.model.Dog;

import java.util.List;

public interface DogRepository extends MongoRepository<Dog, String> {

    List<Dog> findByName(String name);
}
