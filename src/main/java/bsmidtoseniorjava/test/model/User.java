package bsmidtoseniorjava.test.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String firstName;
    private final String lastName;


    public User(UUID id, String name, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}