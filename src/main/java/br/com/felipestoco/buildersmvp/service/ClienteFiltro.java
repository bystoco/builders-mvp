package br.com.felipestoco.buildersmvp.service;

import br.com.felipestoco.buildersmvp.domain.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ClienteFiltro implements Serializable {
    private static final long serialVersionUID = 7336876213362050973L;

    private String nome;
    private String sobrenome;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataIniNascimento;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFimNascimento;
    private String email;

    public ClienteFiltro() {
    }

    /**
     * @return
     */
    public Specification<Cliente> toSpec() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(nome)) {
                final Path<String> pathNome = root.get("nome");
                final Predicate predicateNome = criteriaBuilder.like(pathNome, nome + "%");
                predicates.add(predicateNome);
            }

            if (StringUtils.isNotEmpty(sobrenome)) {
                final Path<String> pathSobrenome = root.get("sobrenome");
                final Predicate predicateSobrenome = criteriaBuilder.like(pathSobrenome, "%" + sobrenome + "%");
                predicates.add(predicateSobrenome);
            }

            if ((!Objects.isNull(dataIniNascimento) && !Objects.isNull(dataFimNascimento))
                    && (dataFimNascimento.isAfter(dataIniNascimento))) {
                final Path<LocalDate> pathDataNascimento = root.get("dataNascimento");
                final Predicate predicateEntreDatas = criteriaBuilder.between(pathDataNascimento, dataIniNascimento, dataFimNascimento);
                predicates.add(predicateEntreDatas);

            } else if (!Objects.isNull(dataNascimento)) {
                final Path<LocalDate> pathDataNascimento = root.get("dataNascimento");
                final Predicate predicateDataNascimento = criteriaBuilder.equal(pathDataNascimento, dataNascimento);
                predicates.add(predicateDataNascimento);
            }

            if (StringUtils.isNotEmpty(email)) {
                final Path<String> pathEmail = root.get("email");
                final Predicate predicateEmail = criteriaBuilder.equal(pathEmail, email);
                predicates.add(predicateEmail);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
