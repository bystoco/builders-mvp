package br.com.felipestoco.buildersmvp.controller;

import br.com.felipestoco.buildersmvp.domain.Cliente;
import br.com.felipestoco.buildersmvp.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.service.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ClienteRepository.class, ClienteService.class})
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;

    private Optional<Cliente> optionalMockCliente;

    @Before
    public void setUp() throws Exception {
        final Cliente clienteMock = new Cliente("Cliente01", "Sobrenome", LocalDate.now(), "email", "999999");
        optionalMockCliente = Optional.of(clienteMock);
    }

    @Test
    public void lista() throws Exception {
        final URI uri = new URI("/clientes");

    }

    @Test
    public void salvar() throws Exception {
    }

    @Test
    public void atualizar() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(optionalMockCliente);

        final URI uri = new URI("/clientes/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void esperaErro400() throws Exception {
        final URI uri = new URI("/clientes/500");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    public void filtrar() throws Exception {
    }

}