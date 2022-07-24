package com.hunky.marketplaceapp.web.rest;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.model.User;
import com.hunky.marketplaceapp.service.ProductService;
import com.hunky.marketplaceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService ps;
    private final UserService us;

    @Autowired
    public ProductController(ProductService ps, UserService us) {
        this.ps = ps;
        this.us = us;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return ps.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return ps.getProductById(id);
    }

    @GetMapping("/products/{id}/users")
    public List<User> getUsersByProductId(@PathVariable Long id) {
        return us.getUsersByProductId(id);
    }

    @PostMapping(value = "/products")
    public Product addProduct(@Valid @RequestBody Product product) {
        return ps.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        ps.deleteProductById(id);
    }

    @PutMapping("/products/{id}")
    Product editProduct(@Valid @RequestBody Product product, @PathVariable Long id) {
        return ps.editProduct(product, id);
    }

}
