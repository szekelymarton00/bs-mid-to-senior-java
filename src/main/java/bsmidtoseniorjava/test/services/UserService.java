package bsmidtoseniorjava.test.services;

import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(@Qualifier("repo") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int addUser(User user) {
        return userRepo.insertUser(user);
    }
}
