package br.unesp.rc.graphql;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.unesp.rc.graphql.dto.CartaoDTO;
import br.unesp.rc.graphql.dto.ClienteDTO;
import br.unesp.rc.graphql.model.Cartao;
import br.unesp.rc.graphql.model.Cliente;
import br.unesp.rc.graphql.model.Conta;
import br.unesp.rc.graphql.model.Historico;

@Controller
public class BankController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ContaService contaService;

    @Autowired
    CartaoService cartaoService;
    @QueryMapping
    public Cliente clienteById(@Argument String id) {
        return clienteService.clienteById(id);
    }

    @QueryMapping
    public Collection<Cliente> findAllCliente() {
        return clienteService.findAllCLientes();
    }

    @MutationMapping
    public Cliente cadastrarCliente(@Argument ClienteDTO cliente) {
        return clienteService.cadastrarClientes(cliente);
    }

    @MutationMapping
    public void removerCliente(@Argument String id) {
        clienteService.removeClienteById(id);
    }

    @MutationMapping
    public Cliente updateCliente(@Argument String id, @Argument ClienteDTO cliente) {
        return clienteService.updateClienteById(id, cliente);
    }

    @QueryMapping
    public List<Conta> findContaCliente(@Argument String id){
        return contaService.findContaCliente(id);
    }

    @MutationMapping
    public Conta cadastrarConta(@Argument String tipo, @Argument String clienteId) {
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

    @MutationMapping
    public Cartao cadastrarCartaoCredito(@Argument CartaoDTO cartao, @Argument String clienteId, @Argument float valor){
        return cartaoService.cadastrarCartao(cartao, valor, clienteId);/*credito */
    }

    @MutationMapping
    public Cartao cadastrarCartaoDebito(@Argument CartaoDTO cartao, @Argument String clienteId, @Argument int numeroConta){
        return cartaoService.cadastrarCartao(cartao, clienteId, numeroConta);/*debito */
    }

    @MutationMapping
    public void pagarOnlineCartao(@Argument String numeroDeSeguranca, @Argument String numeroCartao, @Argument String produto, @Argument float valor){
        cartaoService.pagarOnlineCartao(numeroDeSeguranca, numeroCartao, produto, valor);
    }
    
    @QueryMapping
    public List<Historico> gerarExtrato(@Argument String numeroCartao){
        return cartaoService.gerarExtrato(numeroCartao);
    }

    @QueryMapping
    public List<Cartao> findCartaoCliente(@Argument String clienteId){
        return cartaoService.findCartaoCliente(clienteId);
    }
}
