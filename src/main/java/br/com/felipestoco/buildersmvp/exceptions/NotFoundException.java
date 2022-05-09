package br.com.felipestoco.buildersmvp.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String objeto) {
        super(objeto);
    }
}
