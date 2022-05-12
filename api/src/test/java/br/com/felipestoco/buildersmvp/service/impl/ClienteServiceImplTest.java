package br.com.felipestoco.buildersmvp.service.impl;

import br.com.felipestoco.buildersmvp.api.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.api.service.ClienteService;
import br.com.felipestoco.buildersmvp.api.service.impl.ClienteServiceImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteServiceImplTest {

    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @After
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void salvar() throws Exception {
    }

    @Test
    public void atualizar() throws Exception {
    }

    @Test
    public void filtrar() throws Exception {
    }

}