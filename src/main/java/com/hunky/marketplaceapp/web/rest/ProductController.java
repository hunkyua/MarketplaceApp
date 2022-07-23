package com.hunky.marketplaceapp.web.rest;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService ps;

    @Autowired
    public ProductController(ProductService ps) {
        this.ps = ps;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return ps.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return ps.getProductById(id);
    }

    @PostMapping(value = "/products")
    public Product addProduct(@RequestBody Product product) {
        return ps.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        ps.deleteProductById(id);
    }

    @PutMapping("/products/{id}")
    Product editProduct(@RequestBody Product product, @PathVariable Long id) {
        return ps.editProduct(product, id);
    }

}
