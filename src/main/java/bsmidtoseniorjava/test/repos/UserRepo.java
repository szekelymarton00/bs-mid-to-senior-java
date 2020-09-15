package bsmidtoseniorjava.test.repos;

import java.util.UUID;

import bsmidtoseniorjava.test.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {

    int insertUser(UUID id, User user);

    default int insertUser(User user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

}