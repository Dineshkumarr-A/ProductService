package com.mycompany.productservice.exceptions;

public class ProductControllerSpecificException extends Exception {
    ProductControllerSpecificException(String message) {
        super(message);
    }
}
