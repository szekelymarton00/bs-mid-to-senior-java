package bsmidtoseniorjava.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID task_id;
    @ManyToOne
    private final User owner;
    private final String name;
    private final String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private final Timestamp timestamp;

    public Task(@JsonProperty("task_id") UUID task_id,
                @JsonProperty("owner") User owner,
                @JsonProperty("task_name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("timestamp") Timestamp timestamp) {
        this.task_id = task_id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public User getOwner() {
        return owner;
    }

    public UUID getTask_id() {
        return task_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


}
