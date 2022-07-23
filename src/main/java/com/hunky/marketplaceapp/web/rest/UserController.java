package com.hunky.marketplaceapp.web.rest;

import com.hunky.marketplaceapp.model.User;
import com.hunky.marketplaceapp.service.UserService;
import com.hunky.marketplaceapp.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userService.getUserById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setLastName(newUser.getLastName());
                    user.setAmountOfMoney(newUser.getAmountOfMoney());
                    return userService.addUser(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userService.addUser(newUser);
                });
    }

}
