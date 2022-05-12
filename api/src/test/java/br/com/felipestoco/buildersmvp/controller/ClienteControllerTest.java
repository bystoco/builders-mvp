package br.com.felipestoco.buildersmvp.controller;

import br.com.felipestoco.buildersmvp.api.controller.form.ClienteForm;
import br.com.felipestoco.buildersmvp.api.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.api.service.ClienteService;
import br.com.felipestoco.buildersmvp.domain.Cliente;
import br.com.felipestoco.buildersmvp.domain.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private AutoCloseable autoCloseable;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;

    private List<Cliente> mockLista;
    private String url = "/clientes/";

    @BeforeEach
    void setUp() throws Exception {
        autoCloseable = MockitoAnnotations.openMocks(this);

        final Cliente cli1 = new Cliente("Cliente01", "Sobrenome", LocalDate.of(1981, 11, 18), "cliente01@email.com", "999999");
        cli1.setId(1L);
        final Cliente cli2 = new Cliente("Cliente02", "Sobrenome", LocalDate.of(1951, 5, 26), "cliente02@email.com", "999999");
        cli2.setId(2L);
        final Cliente cli3 = new Cliente("Cliente03", "Sobrenome", LocalDate.of(2002, 7, 17), "cliente03@email.com", "999999");
        cli3.setId(3L);

        mockLista = Arrays.asList(cli1, cli2, cli3);
    }

    @Test
    void lista() throws Exception {
        when(clienteRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(mockLista));

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andReturn();
    }

    @Test
    void salvar() throws Exception {

        final Cliente novoCliente = new Cliente("Novo", "Sobrenome", LocalDate.of(2002, 7, 17), "cliente03@email.com", "999999");
        novoCliente.setId(4L);
        final ClienteForm clienteForm = new ClienteForm("Novo", "Sobrenome", LocalDate.of(2002, 7, 17), "cliente03@email.com", "999999");
        when(clienteService.salvar(Mockito.any(ClienteForm.class))).thenReturn(novoCliente);

        final MvcResult mvcResult = this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteForm))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final MockHttpServletResponse response = mvcResult.getResponse();
        final ClienteDTO clienteSalvo = objectMapper.readValue(response.getContentAsString(), ClienteDTO.class);
        assertEquals(novoCliente.getNome(), clienteSalvo.getNome());
        assertEquals(response.getRedirectedUrl(), "http://localhost/clientes/4");
    }

    @Test
    void get() throws Exception {
        final Optional<Cliente> optionalMockCliente = Optional.of(mockLista.get(0));
        when(clienteRepository.findById(1L)).thenReturn(optionalMockCliente);

        this.mockMvc.perform(MockMvcRequestBuilders.get(url + "1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").exists())
                .andExpect(jsonPath("$.nome").value("Cliente01"))
                .andReturn();
    }

    @Test
    void esperaErro400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url + 500)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

}