package br.com.felipestoco.buildersmvp.api.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String objeto) {
        super(objeto);
    }
}
