package com.hunky.marketplaceapp.service;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.repository.ProductRepository;
import com.hunky.marketplaceapp.repository.UserRepository;
import com.hunky.marketplaceapp.web.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    @Autowired
    public ProductService(UserRepository userRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s was not found.", id)));
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepo.findByUsers_Id(userId);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProductById(Long id) {
        userRepo.findAll().forEach(user ->
                user.getProducts().removeIf(product ->
                        Objects.equals(product.getId(), id)));
        productRepo.deleteById(id);
    }

    @Transactional
    public Product editProduct(Product product, Long id) {
        Product oldProduct = getProductById(id);

        if (oldProduct != null) {
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            return oldProduct;
        } else {
            product.setId(id);
            return product;
        }
    }
}
