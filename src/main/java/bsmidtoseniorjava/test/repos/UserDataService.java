package bsmidtoseniorjava.test.repos;

import bsmidtoseniorjava.test.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("repo")
public class UserDataService implements UserRepo {

    private static List<User> dataBase = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        dataBase.add(new User(id, user.getName(), user.getFirstName(), user.getLastName()));
        return 1;
    }
}
