package bsmidtoseniorjava.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    private final String name;
    private final String description;
    private final Timestamp timestamp;

    public Task(@JsonProperty("id") UUID id,
                @JsonProperty("task_name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("timestamp") Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
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
