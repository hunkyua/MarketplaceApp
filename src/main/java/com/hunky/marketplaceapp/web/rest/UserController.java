package com.hunky.marketplaceapp.web.rest;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.model.User;
import com.hunky.marketplaceapp.service.ProductService;
import com.hunky.marketplaceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService us;
    private final ProductService ps;

    @Autowired
    public UserController(UserService us, ProductService ps) {
        this.us = us;
        this.ps = ps;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return us.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return us.getUserById(id);
    }

    @GetMapping("/users/{id}/products")
    public List<Product> getProductsByUserId(@PathVariable Long id) {
        return ps.getProductsByUserId(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return us.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        us.deleteById(id);
    }

    @PutMapping("/users/{id}")
    User editUser(@RequestBody User user, @PathVariable Long id) {
        return us.editUser(user, id);
    }

    @PatchMapping("/users/{userId}/products/{productId}")
    User buyProductByUser(@PathVariable Long userId, @PathVariable Long productId) {
        return us.buyProduct(userId, productId);
    }
}
