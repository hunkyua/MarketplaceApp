package com.hunky.marketplaceapp.web.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String firstName, String productName) {
        super("User " + firstName + " doesn't have enough money to buy " + productName);
    }
}
