package bsmidtoseniorjava.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "bsuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID user_id;
    @Column(name="username")
    private final String username;
    @Column(name="first_name")
    private final String firstName;
    @Column(name="last_name")
    private final String lastName;
    @Column(name="ownedtask")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name ="task_id"))
    private final Collection<Task> ownedtask;


    public User(@JsonProperty("user_id") UUID user_id,
                @JsonProperty("username") String username,
                @JsonProperty("first_name") String firstName,
                @JsonProperty("last_name") String lastName,
                @JsonProperty("ownedtask") Collection<Task> ownedtask) {
        this.user_id = user_id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownedtask = ownedtask;
    }

    public UUID getUser_id() {
        return user_id;
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

    public Collection<Task> getOwnedtask() {
        return ownedtask;
    }

}
