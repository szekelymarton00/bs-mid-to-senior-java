package bsmidtoseniorjava.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String firstName;
    private final String lastName;


    public User(@JsonProperty("id") UUID id,
                @JsonProperty("username") String name,
                @JsonProperty("first_name") String firstName,
                @JsonProperty("last_name") String lastName) {
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
