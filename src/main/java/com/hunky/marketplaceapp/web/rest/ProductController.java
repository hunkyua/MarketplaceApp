package com.hunky.marketplaceapp.web.rest;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.service.ProductService;
import com.hunky.marketplaceapp.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product newProduct) {
        return productService.addProduct(newProduct);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return productService.getProductById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setUser(newProduct.getUser());
                    return productService.addProduct(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productService.addProduct(newProduct);
                });
    }

}
