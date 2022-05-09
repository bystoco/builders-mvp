package br.com.felipestoco.buildersmvp.service;

import br.com.felipestoco.buildersmvp.controller.form.ClienteForm;
import br.com.felipestoco.buildersmvp.domain.Cliente;
import br.com.felipestoco.buildersmvp.domain.dto.ClienteDTO;
import br.com.felipestoco.buildersmvp.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    /**
     * @param form
     */
    Cliente salvar(ClienteForm form);

    /**
     * @param id
     * @param clienteForm
     * @return
     * @throws NotFoundException Para Cliente nao encontrado.
     */
    Cliente salvar(Long id, ClienteForm clienteForm) throws NotFoundException;

    /**
     * @param filtro
     * @return
     */
    Page<ClienteDTO> filtrar(ClienteFiltro filtro, Pageable paginacal);

}
