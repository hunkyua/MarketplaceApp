package com.hunky.marketplaceapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Scope("prototype")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Product() {

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
