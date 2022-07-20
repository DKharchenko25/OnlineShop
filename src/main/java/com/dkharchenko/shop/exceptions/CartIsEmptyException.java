package com.dkharchenko.shop.exceptions;

public class CartIsEmptyException extends Exception {
    public CartIsEmptyException(String message) {
        super(message);
    }
}
