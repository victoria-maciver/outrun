package uk.co.outrun.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.co.outrun.model.Dog;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Integer> {

    List<Dog> findByRegName(String regName);

    @Query(value = "SELECT * FROM dog WHERE sire_id = ?1 OR dam_id = ?1", nativeQuery = true)
    List<Dog> findChildren(int id);

    @Query(value = "SELECT * FROM dog WHERE sire_id = ?2 AND dam_id = ?3 AND id != ?1", nativeQuery = true)
    List<Dog> findSiblings(int id, int sireId, int damId);

}
