package com.hunky.marketplaceapp.service;

import com.hunky.marketplaceapp.model.Product;
import com.hunky.marketplaceapp.repository.ProductRepository;
import com.hunky.marketplaceapp.web.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s was not found.", id)));
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByUsers_Id(userId);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
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
