package com.hunky.marketplaceapp.web.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String firstName, String productName) {
        super(String.format("%s doesn't have enough money to buy a %s", firstName, productName));
    }
}
