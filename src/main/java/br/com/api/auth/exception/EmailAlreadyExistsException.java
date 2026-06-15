package br.com.api.auth.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(){
        super("Email already exists");
    }
}
