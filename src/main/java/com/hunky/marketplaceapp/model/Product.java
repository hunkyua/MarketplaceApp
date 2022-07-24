package com.hunky.marketplaceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field name can't be blank")
    @NotNull(message = "Field name can't be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Field price can't be null")
    @Digits(integer=10, fraction=2)
    @Column(name = "price")
    private Double price;

    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<User> users = new ArrayList<>();

}
