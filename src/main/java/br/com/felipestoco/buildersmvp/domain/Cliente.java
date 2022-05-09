package br.com.felipestoco.buildersmvp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Validated
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Getter
    private Long id;

    @NotBlank
    @Size(max = 255, min = 1)
    @Getter @Setter
    private String nome;

    @NotBlank
    @Size(max = 255, min = 1)
    @Getter @Setter
    private String sobrenome;

    @NotNull @Past
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    @Getter @Setter
    private LocalDate dataNascimento;

    @NotNull
    @Email
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String celular;

    @Getter
    private boolean situacao;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(nullable = false, updatable = false)
    @Getter
    private LocalDateTime dataHoraCadastro;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Getter
    private LocalDateTime dataHoraAtualizacao;

    @PrePersist
    private void naCriacao() {
        dataHoraCadastro = dataHoraAtualizacao = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
    }

    @PreUpdate
    private void naAtualizacao() {
        dataHoraAtualizacao = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
    }

    /**
     * Construtor padrao.
     */
    public Cliente() {
    }

    /**
     * @param nome
     * @param sobrenome
     * @param dataNascimento
     * @param email
     * @param celular
     */
    public Cliente(String nome, String sobrenome, LocalDate dataNascimento, String email, String celular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.celular = celular;
        this.situacao = true;
    }

    /**
     * Altera a situacao do Cliente para false.
     */
    public void inativar() {
        this.situacao = false;
    }

    @Transactional
    public int getIdade() {
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }
}
