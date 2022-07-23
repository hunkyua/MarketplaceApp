package com.hunky.marketplaceapp.repository;

import com.hunky.marketplaceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    List<User> findByProducts_Id(Long id);

    @Override
    void deleteById(Long id);
}

