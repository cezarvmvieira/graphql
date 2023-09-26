package br.unesp.rc.graphql;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.unesp.rc.graphql.dto.ClienteDTO;
import br.unesp.rc.graphql.model.Cliente;
import br.unesp.rc.graphql.model.Conta;

@Controller
public class BankController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ContaService contaService;

    @QueryMapping
    public Cliente clienteById(@Argument String id) {
        return clienteService.clienteById(id);
    }

    @MutationMapping
    public Collection<Cliente> cadastrarCliente(@Argument ClienteDTO cliente) {
        return clienteService.cadastrarClientes(cliente);
    }

    @MutationMapping
    public Collection<Cliente> removerCliente(@Argument String id) {
        return clienteService.removeClienteById(id);
    }

    @MutationMapping
    public Collection<Cliente> updateCliente(@Argument String id, @Argument ClienteDTO cliente) {
        return clienteService.updateClienteById(id, cliente);
    }

    @MutationMapping
    public Collection<Conta> cadastrarConta(@Argument String tipo, @Argument String clienteId) {
        return contaService.cadastrarConta(tipo, clienteId);
    }

    @MutationMapping
    public Conta depositarConta(@Argument int numero, @Argument float valor) {
        return contaService.depositarConta(numero, valor);
    }

    @MutationMapping
    public Conta sacarConta(@Argument int numero, @Argument float valor) {
        return contaService.sacarConta(numero, valor);
    }
}
