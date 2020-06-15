package uk.co.outrun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uk.co.outrun.model.Dog;

import java.util.List;

public interface DogRepository extends MongoRepository<Dog, String> {

    List<Dog> findByRegName(String regName);

    Dog findByDogId(Integer dogId);

    @Query("{ 'regName': { '$regex' : '.*?0.*' }}")
    List<Dog> searchByRegName(String regName);

    @Query("{ 'sex': { '$regex' : '.*?0.*' }}")
    List<Dog> searchBySex(String sex);

    @Query("{'regName' : { '$regex' : '.*?0.*' }, " +
            "'sex' : { '$regex' : '.*?1.*' }}")
    List<Dog> customSearch(String regName, String sex);




}
