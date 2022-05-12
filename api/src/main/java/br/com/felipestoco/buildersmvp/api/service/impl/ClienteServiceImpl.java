package br.com.felipestoco.buildersmvp.api.service.impl;

import br.com.felipestoco.buildersmvp.api.controller.form.ClienteForm;
import br.com.felipestoco.buildersmvp.api.exceptions.NotFoundException;
import br.com.felipestoco.buildersmvp.api.repository.ClienteRepository;
import br.com.felipestoco.buildersmvp.api.service.ClienteFiltro;
import br.com.felipestoco.buildersmvp.api.service.ClienteService;
import br.com.felipestoco.buildersmvp.domain.Cliente;
import br.com.felipestoco.buildersmvp.domain.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(ClienteForm form) {
        return clienteRepository.save(form.converter());
    }

    @Override
    public Cliente salvar(Long id, ClienteForm form) throws NotFoundException {
        final Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            final Cliente cliente = optional.get();
            if (!Objects.isNull(form.getNome())) {
                cliente.setNome(form.getNome());
            }
            if (!Objects.isNull(form.getSobrenome())) {
                cliente.setSobrenome(form.getSobrenome());
            }
            if (!Objects.isNull(form.getDataNascimento())) {
                cliente.setDataNascimento(form.getDataNascimento());
            }
            if (!Objects.isNull(form.getEmail())) {
                cliente.setEmail(form.getEmail());
            }
            if (!Objects.isNull(form.getCelular())) {
                cliente.setCelular(form.getCelular());
            }

            return clienteRepository.save(cliente);
        }
        throw new NotFoundException(String.format("Cliente com ID: %d", id));
    }

    @Override
    public Page<ClienteDTO> filtrar(ClienteFiltro filtro, Pageable paginacao) {
        final Page<Cliente> clientes = clienteRepository.findAll(filtro.toSpec(), paginacao);
        return ClienteDTO.converter(clientes);
    }
}
