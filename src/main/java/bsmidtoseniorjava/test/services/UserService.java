package bsmidtoseniorjava.test.services;

import bsmidtoseniorjava.test.model.Task;
import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
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
        jdbcTemplate.update("INSERT INTO bsuser(id, username, first_name, last_name, ownedtask) VALUES(?,?,?,?,?)", id, user.getUsername(),user.getFirstName(),user.getLastName(),user.getOwnedtask());
    }

    public Iterable<User> getAllUsers() {
        return userRepo.listAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepo.findById(id);
    }

    public int deleteUser(UUID id) {
        jdbcTemplate.update("DELETE FROM bsuser WHERE id = ?",id);
        return 1;
    }

    public int updateUser(UUID id, User user) {
        jdbcTemplate.update("UPDATE bsuser SET username = ? WHERE id = ?",user.getUsername(),id);
        return 1;
    }

    public Set<Task> getAllTasksOfUserById(UUID id) {
       return userRepo.findById(id).get().getOwnedtask();
    }
}
