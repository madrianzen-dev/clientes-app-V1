package com.madrianzen.clientes.service;

import com.madrianzen.clientes.model.Cliente;
import com.madrianzen.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscar(Long id) {
        return repository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
