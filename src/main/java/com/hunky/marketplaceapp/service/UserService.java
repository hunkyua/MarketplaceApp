package com.hunky.marketplaceapp.service;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.model.User;
import com.hunky.marketplaceapp.repository.ProductRepository;
import com.hunky.marketplaceapp.repository.UserRepository;
import com.hunky.marketplaceapp.web.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id %s was not found.", id)));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User buyProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s was not found.", userId)));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s was not found.", productId)));
        user.buyProduct(product);
        return user;
    }

    @Transactional
    public User editUser(User user, Long id) {
        User oldUser = getUserById(id);

        if (oldUser != null) {
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setAmountOfMoney(user.getAmountOfMoney());
            return oldUser;
        } else {
            user.setId(id);
            return user;
        }
    }
}
