package br.com.felipestoco.buildersmvp.controller.form;

import br.com.felipestoco.buildersmvp.domain.Cliente;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
public class ClienteForm {

    @Min(3)
    private String nome;
    @Min(3)
    private String sobrenome;
    @Past
    private LocalDate dataNascimento;
    @Email
    private String email;
    private String celular;
    private boolean situacao;

    public ClienteForm() {
    }

    public ClienteForm(String nome, String sobrenome, LocalDate dataNascimento, String email, String celular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.situacao = true;
    }

    public ClienteForm(String nome, String sobrenome, LocalDate dataNascimento, String email, String celular, boolean situacao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.situacao = situacao;
    }

    public Cliente converter() {
        return new Cliente(nome, sobrenome, dataNascimento, email, celular);
    }

}
