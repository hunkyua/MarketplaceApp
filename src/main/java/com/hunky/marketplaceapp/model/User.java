package com.hunky.marketplaceapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Scope("prototype")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "amountOfMoney")
    private double amountOfMoney;

    @OneToMany(mappedBy="user")
    private Set<Product> product;

    public User() {}

    public User(String name, String lastName, double amountOfMoney) {
        this.name = name;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
    }
}
