package br.com.felipestoco.buildersmvp.controller;

import br.com.felipestoco.buildersmvp.controller.form.ClienteForm;
import br.com.felipestoco.buildersmvp.domain.Cliente;
import br.com.felipestoco.buildersmvp.domain.dto.ClienteDTO;
import br.com.felipestoco.buildersmvp.domain.dto.ClienteDetalhadoDTO;
import br.com.felipestoco.buildersmvp.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.service.ClienteFiltro;
import br.com.felipestoco.buildersmvp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public Page<ClienteDTO> lista(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {
        final Page<Cliente> clientes = clienteRepository.findAll(paginacao);
        return ClienteDTO.converter(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder) {
        final Cliente novoCliente = clienteService.salvar(form);

        final URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(novoCliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(novoCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteForm form) {
        final Cliente clienteAtualizado = clienteService.salvar(id, form);
        return ResponseEntity.ok(new ClienteDTO(clienteAtualizado));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDetalhadoDTO> get(@PathVariable("id") long id) {
        final Optional<Cliente> optional = clienteRepository.findById(id);
        return optional.map(cliente -> ResponseEntity.ok(new ClienteDetalhadoDTO(cliente)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/filtrar")
    public Page<ClienteDTO> filtrar(ClienteFiltro filtro, Pageable paginacao) {
        return clienteService.filtrar(filtro, paginacao);
    }

}
