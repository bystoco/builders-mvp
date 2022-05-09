package br.com.felipestoco.buildersmvp.domain.dto;

import br.com.felipestoco.buildersmvp.domain.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Getter
@Setter
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = -8453884721955052657L;

    private String nome;
    private String sobrenome;
    private int idade;
    private String email;
    private String celular;
    private String situacao;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.idade = cliente.getIdade();
        this.email = cliente.getEmail();
        this.celular = cliente.getCelular();
        this.situacao = cliente.isSituacao() ? "Ativo" : "Inativo";
    }

    public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDTO::new);
    }

}
