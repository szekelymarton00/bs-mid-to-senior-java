package bsmidtoseniorjava.test.services;

import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(@Qualifier("postgres") UserRepo userRepo, JdbcTemplate jdbcTemplate) {
        this.userRepo = userRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("INSERT INTO bsuser(user_id, username, first_name, last_name, ownedtask) VALUES(?,?,?,?,?)", id, user.getUsername(),user.getFirstName(),user.getLastName(),user.getOwnedtask());
    }

    public List<User> getAllUsers() {
        return userRepo.listAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepo.findById(id);
    }

    public int deleteUser(UUID id) {
        jdbcTemplate.update("DELETE FROM bsuser WHERE user_id = ?",id);
        return 1;
    }

    public int updateUser(UUID id, User user) {
        jdbcTemplate.update("UPDATE bsuser SET username = ? WHERE user_id = ?",user.getUsername(),id);
        return 1;
    }

    public String getAllTasksOfUserById(UUID id) {
       return userRepo.findById(id).get().getOwnedtask().toString();
    }
}
