package bsmidtoseniorjava.test.repos;

import java.util.List;
import java.util.Optional;
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

    List<User> selectAllUsers();

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User user);

    Optional<User> selectUserById(UUID id);
}