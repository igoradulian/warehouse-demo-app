package com.warehouse.exceptions;

/**
 * @author igoradulyan on 12/19/24
 * @project IntelliJ IDEA
 */
public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
