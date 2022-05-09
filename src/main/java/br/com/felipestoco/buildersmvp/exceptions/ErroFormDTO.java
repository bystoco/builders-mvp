package br.com.felipestoco.buildersmvp.exceptions;

import lombok.Getter;

@Getter
public class ErroFormDTO {

    private String campo;
    private String erro;

    public ErroFormDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
