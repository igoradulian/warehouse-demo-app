package com.warehouse.exceptions;

/**
 * @author igoradulyan on 12/19/24
 * @project IntelliJ IDEA
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
