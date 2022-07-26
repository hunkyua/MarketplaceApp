package com.hunky.marketplaceapp.repository;

import com.hunky.marketplaceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByUsers_Id(Long id);

    @Override
    void deleteById(Long id);

}
