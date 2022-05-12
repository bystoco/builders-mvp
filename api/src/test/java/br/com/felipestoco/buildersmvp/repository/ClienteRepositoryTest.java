package br.com.felipestoco.buildersmvp.repository;

import br.com.felipestoco.buildersmvp.api.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.domain.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void save() {
        final Cliente cliente = new Cliente("Nome", "Sobrenome", LocalDate.of(1981, 11, 18), "cliente@email.com", "(99) 9999-9999");

        clienteRepository.save(cliente);
        final Optional<Cliente> one = clienteRepository.findOne(Example.of(cliente));
        assertEquals("Nome", cliente.getNome());
    }

    @Test
    public void saveErroValidacao_Email() {
        final Cliente cliente = new Cliente("Nome", "Sobrenome", LocalDate.of(1981, 11, 18),
                "emailerrado", "(99) 9999-9999");

        final ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            clienteRepository.saveAndFlush(cliente);
        });

        assertNotNull(exception);
        assertTrue(exception.getConstraintViolations().size() == 1);
        final Path propertyPath = exception.getConstraintViolations().stream().findFirst().get().getPropertyPath();
        assertEquals("email", propertyPath.toString());

    }

    @Test
    public void saveErroValidacao_DataNascimento() {
        final Cliente cliente = new Cliente("Nome", "Sobrenome", LocalDate.now(),
                "cliente@email.com", "(99) 9999-9999");

        final ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            clienteRepository.saveAndFlush(cliente);
        });

        assertNotNull(exception);
        assertTrue(exception.getConstraintViolations().size() == 1);
        final Path propertyPath = exception.getConstraintViolations().stream().findFirst().get().getPropertyPath();
        assertEquals("dataNascimento", propertyPath.toString());

    }


}