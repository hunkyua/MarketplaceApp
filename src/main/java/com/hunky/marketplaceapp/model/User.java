package com.hunky.marketplaceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hunky.marketplaceapp.web.exceptions.NotEnoughMoneyException;
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
@EqualsAndHashCode(exclude = {"products"})
@ToString(exclude = {"products"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field firstName can't be blank")
    @NotNull(message = "Field firstName can't be null")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Field lastName can't be blank")
    @NotNull(message = "Field lastName can't be null")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Field amountOfMoney can't be null")
    @Digits(integer=10, fraction=2)
    @Column(name = "amountOfMoney")
    private Double amountOfMoney;

    @JsonBackReference
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public void buyProduct(Product product) throws NotEnoughMoneyException {
        double balance = this.getAmountOfMoney() - product.getPrice();
        if (balance >= 0.0) {
            this.setAmountOfMoney(balance);
        } else {
            throw new NotEnoughMoneyException(this.firstName, product.getName());
        }

        products.add(product);
    }
}
