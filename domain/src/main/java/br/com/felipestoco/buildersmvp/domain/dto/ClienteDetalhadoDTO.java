package br.com.felipestoco.buildersmvp.domain.dto;

import br.com.felipestoco.buildersmvp.domain.Cliente;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ClienteDetalhadoDTO implements Serializable {
    private static final long serialVersionUID = -1119675933866911576L;

    private long ID;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private int idade;
    private String email;
    private String celular;
    private String situacao;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAtualizacao;

    /**
     * Contrutor padrao.
     */
    public ClienteDetalhadoDTO() {
    }

    public ClienteDetalhadoDTO(Cliente cliente) {
        this.ID = cliente.getId();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.dataNascimento = cliente.getDataNascimento();
        this.idade = cliente.getIdade();
        this.email = cliente.getEmail();
        this.celular = cliente.getCelular();
        this.situacao = cliente.isSituacao() ? "Ativo" : "Inativo";
        this.dataHoraCadastro = cliente.getDataHoraCadastro();
        this.dataHoraAtualizacao = cliente.getDataHoraAtualizacao();
    }
}
