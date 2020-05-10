package uk.co.outrun.model;

import org.springframework.data.annotation.Id;

public class Dog {

    @Id
    private String id;

    private String name;

    public Dog() {

    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Dog[id='%s', name='%s']", id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
