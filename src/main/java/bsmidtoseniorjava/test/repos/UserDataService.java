package bsmidtoseniorjava.test.repos;

import bsmidtoseniorjava.test.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("repo")
public class UserDataService implements UserRepo {

    private static List<User> dataBase = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        dataBase.add(new User(id, user.getName(), user.getFirstName(), user.getLastName()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return dataBase;
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> potentialUser = selectUserById(id);
        if (potentialUser.isEmpty()) {
            return 0;
        }
        dataBase.remove(potentialUser.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return selectUserById(id).map(u -> {
            int indexOfUserToDelete = dataBase.indexOf(user);
            if (indexOfUserToDelete >= 0) {
                dataBase.set(indexOfUserToDelete, user);
                return 1;
            }
            return 0;
            //TODO: implement catch part!
        }).orElse(0);
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return dataBase.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
