package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.nourishnet.model.User;
import com.nourishnet.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello from User Controller";
    }


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {

        return userService.loginUser(
                user.getEmail(),
                user.getPassword()
        );

    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);

    }


    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {

        return userService.updateUser(id, user);

    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        return userService.deleteUser(id);

    }
@GetMapping("/volunteers")
public List<User> getVolunteers() {

    return userService.getVolunteers();

}



}