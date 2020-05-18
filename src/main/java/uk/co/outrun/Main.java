package uk.co.outrun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.outrun.model.Dog;
import uk.co.outrun.repository.DogRepository;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private DogRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) {

        Dog felix = repository.findByDogId(1);

        Dog brin = repository.findByDogId(felix.getSireId());
        Dog fig = repository.findByDogId(felix.getDamId());

        System.out.println(felix.getCallName() + "'s sire is " + brin.getCallName() + " and his dam is "
                + fig.getCallName());


        Dog lance = Dog.builder()
                .regName("Seren Lance")
                .callName("Lance")
        repository.save(lance);

        List<Dog> wee = repository.findByRegName("Seren Lance");

        System.out.println(wee.get(0).getCallName() + " is the new dog!");

        Dog ben = new Dog("Ben");


        lance.setSireId(ben.getDogId());

//
//        for (Dog dog : repository.findAll()) {
//            System.out.println(dog);
//        }
//        System.out.println();
//
//
//        repository.findAll();
    }
}
