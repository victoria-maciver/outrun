package uk.co.outrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.outrun.model.Dog;
import uk.co.outrun.service.DogService;

import java.util.List;

// TODO - make this configurable
@CrossOrigin
@RestController
public class RESTController {

    @Autowired
    DogService dogService;


    @GetMapping(value = "/dog/all")
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping(value = "/dog/{id}")
    public Dog getDog(@PathVariable int id) {
        return dogService.getDogById(id);
    }


    @GetMapping(value = "/dog/{id}/familytree")
    public List<Dog> getFamilyTree(@PathVariable int id) {
        return dogService.getFamilyTree(id);
    }

    @GetMapping(value = "/dog/{id}/children")
    public List<Dog> getChildren(@PathVariable int id) {
        return dogService.getChildren(id);
    }

    @GetMapping(value = "/dog/{id}/siblings")
    public List<Dog> getSiblings(@PathVariable int id) {
        return dogService.getSiblings(id);
    }

}