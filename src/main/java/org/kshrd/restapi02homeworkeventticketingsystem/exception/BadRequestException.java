package org.kshrd.restapi02homeworkeventticketingsystem.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
