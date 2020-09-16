package bsmidtoseniorjava.test.services;

import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(@Qualifier("postgres") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int addUser(User user) {
        return userRepo.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userRepo.selectAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepo.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return userRepo.deleteUserById(id);
    }

    public int updateUser(UUID id, User user) {
        return userRepo.updateUserById(id,user);
    }
}
