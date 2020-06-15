package uk.co.outrun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.outrun.model.DogNum;

@Repository
public interface DogNumRepository extends MongoRepository<DogNum, String> {
    DogNum findTopByOrderByIdDesc();
}
