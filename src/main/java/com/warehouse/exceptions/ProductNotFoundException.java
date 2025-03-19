package com.warehouse.exceptions;

/**
 * @author igoradulyan on 12/17/24
 * @project IntelliJ IDEA
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
