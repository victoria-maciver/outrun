package uk.co.outrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.outrun.model.Dog;
import uk.co.outrun.model.SearchRequest;
import uk.co.outrun.service.DogService;

import java.util.List;

@RestController
public class RESTController {

    @Autowired
    DogService dogService;

    @PostMapping(value = "/dog/find")
    public List<Dog> find(@RequestBody SearchRequest req) {
        System.out.println("Searching for \"" + req.getRegName() + "\"...");
        List<Dog> dogByRegName = dogService.getDogByRegName(req.getRegName());
        System.out.println("Found: ");
        for (Dog dog : dogByRegName) {
            System.out.println(" - " + dog.getRegName());
        }
        return dogByRegName;
    }

    @PostMapping(value = "/dog/regName")
    public List<Dog> regName(@RequestBody SearchRequest req) {
        System.out.println("Searching for \"" + req.getRegName() + "\"...");
        List<Dog> dogByRegName = dogService.searchByRegName(req.getRegName());
        System.out.println("Found: ");
        for (Dog dog : dogByRegName) {
            System.out.println(" - " + dog.getRegName());
        }
        return dogByRegName;
    }

    @PostMapping(value = "/dog/search")
    public List<Dog> search(@RequestBody SearchRequest req) {
        System.out.println("Searching for \"" + req.getRegName() + "\"...");
        List<Dog> results = dogService.search(req);
        System.out.println("Found: ");
        for (Dog dog : results) {
            System.out.println(" - " + dog.toString());
        }
        return results;
    }

    @GetMapping(value = "/dog/all")
    public List<Dog> all() {
        System.out.println("Listing all dogs...");
        List<Dog> allDogs = dogService.getAllDogs();
        System.out.println("Found: ");
        for (Dog dog : allDogs) {
            System.out.println(" - " + dog.getRegName());
        }
        return allDogs;
    }
} 