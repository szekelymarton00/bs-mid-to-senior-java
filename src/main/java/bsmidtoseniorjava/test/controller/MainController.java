package bsmidtoseniorjava.test.controller;

import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("api/user")
public class MainController {

    private final UserService userService;

    private static final Logger log;

    static {
        log = LogManager.getLogger(MainController.class);
    }

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        log.debug("Adding user: " + user.getUsername() +" to db");
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.debug("Retrieving all users in db");
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        //TODO: implement catch part in case of invalid/ not found ID
        log.debug("Retrieving user with ID: " + id);
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        log.debug("Deleting user with ID: " + id);
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @RequestBody User userToUpdate) {
        if (userService.getUserById(id).isPresent()) {
            log.debug("Updating user with ID: " + id);
            userService.updateUser(id,userToUpdate);
        }
    }
}
