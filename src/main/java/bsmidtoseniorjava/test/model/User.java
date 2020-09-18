package bsmidtoseniorjava.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bsuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @Column(name="username")
    private final String username;
    @Column(name="first_name")
    private final String firstName;
    @Column(name="last_name")
    private final String lastName;
    @Column(name="ownedtask")
    private final Set<Task> ownedtask;


    public User(@JsonProperty("id") UUID id,
                @JsonProperty("username") String username,
                @JsonProperty("first_name") String firstName,
                @JsonProperty("last_name") String lastName,
                @JsonProperty("ownedtask") Set<Task> ownedtask) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownedtask = ownedtask;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Task> getOwnedtask() {
        return ownedtask;
    }

}
