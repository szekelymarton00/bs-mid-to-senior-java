package bsmidtoseniorjava.test.controller;

import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        //TODO: implement catch part in case of invalid/ not found ID
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @RequestBody User userToUpdate) {
        if (userService.getUserById(id).isPresent()) {
            userService.updateUser(id,userToUpdate);
        }
    }
}
