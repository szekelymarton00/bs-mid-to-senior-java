package bsmidtoseniorjava.test.controller;

import bsmidtoseniorjava.test.model.Task;
import bsmidtoseniorjava.test.model.User;
import bsmidtoseniorjava.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Controller
public class TaskController {

    private final UserService userService;

    @Autowired
    public TaskController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "api/user/{id}/task")
    public void addTask(@RequestBody Task task) {
        //userService.getAllTasksOfUserById(user);
    }

    @GetMapping
    public Set<Task> getAllUsers(@PathVariable("id") UUID id) {
        return userService.getAllTasksOfUserById(id);
    }



}
