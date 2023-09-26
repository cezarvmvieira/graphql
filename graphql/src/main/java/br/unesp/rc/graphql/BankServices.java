package br.unesp.rc.graphql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unesp.rc.graphql.dto.CartaoDTO;
import br.unesp.rc.graphql.dto.ClienteDTO;
import br.unesp.rc.graphql.model.Cartao;
import br.unesp.rc.graphql.model.Cliente;
import br.unesp.rc.graphql.model.Conta;
import br.unesp.rc.graphql.model.Contato;
import br.unesp.rc.graphql.model.Endereco;
import br.unesp.rc.graphql.model.Historico;

@Service
class ClienteService {
    Map<String, Cliente> clientes = new HashMap<>();
    Map<String, Endereco> enderecos = new HashMap<>();
    Map<String, Contato> contatos = new HashMap<>();

    Collection<Cliente> cadastrarClientes(ClienteDTO cliente) {
        Endereco endereco = new Endereco(UUID.randomUUID().toString(), cliente.getRua(), cliente.getBairro(),
                cliente.getCidade(), cliente.getEstado(), cliente.getCep(), cliente.getNumero());
        Contato contato = new Contato(UUID.randomUUID().toString(), cliente.getTipo(), cliente.getValor());
        var newCLiente = new Cliente(UUID.randomUUID().toString(), cliente.getNome(), cliente.getCpf(), endereco,
                contato);
        clientes.put(newCLiente.getClienteId(), newCLiente);
        return clientes.values();
    }

    Cliente clienteById(String id) {
        return clientes.get(id);
    }

    Collection<Cliente> removeClienteById(String id) {
        clientes.remove(id);
        return clientes.values();
    }

    Collection<Cliente> updateClienteById(String id, ClienteDTO cliente) {
        Endereco endereco = new Endereco(clientes.get(id).getEndereco().getEnderecoId(), cliente.getRua(),
                cliente.getBairro(), cliente.getCidade(), cliente.getEstado(), cliente.getCep(), cliente.getNumero());
        Contato contato = new Contato(clientes.get(id).getContato().getContatoId(), cliente.getTipo(),
                cliente.getValor());
        var newCLiente = new Cliente(id, cliente.getNome(), cliente.getCpf(), endereco, contato);
        clientes.put(id, newCLiente);
        return clientes.values();
    }

    Cliente updateClienteById(Cliente cliente) {
        clientes.put(cliente.getClienteId(), cliente);
        return cliente;
    }

    Cliente associarConta(String clienteId, int numeroConta) {
        Cliente cliente = clienteById(clienteId);
        if (cliente != null) {
            List<Integer> contasCliente = cliente.getContas();
            contasCliente.add(numeroConta);
            cliente.setContas(contasCliente);
            clientes.put(clienteId, cliente);
        }
        return cliente;
    }
}

@Service
class ContaService {
    Map<Integer, Conta> contas = new HashMap<>();
    @Autowired
    ClienteService clienteService;

    Collection<Conta> cadastrarConta(String tipo, String clienteId) {
        Conta conta = new Conta(contas.size() + 1, 0, tipo);
        if (clienteService.associarConta(clienteId, conta.getNumero()) != null) {
            contas.put(conta.getNumero(), conta);
            return contas.values();
        }
        return null;
    }

    Conta findConta(int numero) {
        return contas.get(numero);
    }

    List<Conta> findContaCliente(String clienteId) {
        Cliente cliente = clienteService.clienteById(clienteId);
        List<Conta> clienteContas = new ArrayList<>();
        for (Integer i : cliente.getContas()) {
            clienteContas.add(findConta(i));
        }
        return clienteContas;
    }

    Conta depositarConta(int numero, float valor) {
        Conta conta = findConta(numero);
        conta.setSaldo(conta.getSaldo() + valor);
        contas.put(numero, conta);
        return conta;
    }

    Conta sacarConta(int numero, float valor) {
        Conta conta = findConta(numero);
        conta.setSaldo(conta.getSaldo() - valor);
        contas.put(numero, conta);
        return conta;
    }
}

@Service
class CartaoService {
    Map<String, Cartao> cartoes = new HashMap<>();
    @Autowired
    ClienteService clienteService;
    @Autowired
    ContaService contaService;

    /* cadastrando cartão de crédito */
    Cartao cadastrarCartao(CartaoDTO dto, float limite, String clienteId) {
        Cliente cliente = clienteService.clienteById(clienteId);
        Cartao cartao = null;
        if (cliente != null) {
            List<String> cartoesCliente = cliente.getCartoes();
            cartao = new Cartao(dto.getDataValidade(), dto.getTipo(), dto.getSenha(), dto.getNumeroDeSeguranca());
            cartao.setLimite(limite);
            cartoesCliente.add(cartao.getNumero());
            /* associando cartão ao cliente */
            cliente.setCartoes(cartoesCliente);
            clienteService.updateClienteById(cliente);
            cartoes.put(cartao.getNumero(), cartao);
        }
        return cartao;
    }

    /* cadastrando cartão de débito */
    Cartao cadastrarCartao(CartaoDTO dto, String clienteId, int numeroConta) {
        Cliente cliente = clienteService.clienteById(clienteId);
        Cartao cartao = null;
        if (cliente != null) {
            List<String> cartoesCliente = cliente.getCartoes();
            cartao = new Cartao(dto.getDataValidade(), dto.getTipo(), dto.getSenha(), dto.getNumeroDeSeguranca());
            /* associando cartão a conta */
            cartao.setNumeroConta(numeroConta);
            cartoesCliente.add(cartao.getNumero());
            cliente.setCartoes(cartoesCliente);
            /* associando cartão ao cliente */
            clienteService.updateClienteById(cliente);
            cartoes.put(cartao.getNumero(), cartao);
        }
        return cartao;
    }

    void pagarOnlineCartao(String numeroDeSeguranca, String numeroCartao, String produto, float valor) {
        Date dataCompra = new Date();
        Historico historico = new Historico(dataCompra, produto, valor);
        Cartao cartao = cartoes.get(numeroCartao);
        List<Historico> historicos = cartao.getHistorico();
        historicos.add(historico);
        if (cartao.getNumeroDeSeguranca().equals(numeroDeSeguranca))
            switch (cartao.getTipo()) {
                case "Credito":
                    cartao.setLimite(cartao.getLimite() - valor);
                    break;
                case "Debito":
                    contaService.sacarConta(cartao.getNumeroConta(), valor);
                    break;
            }
        cartao.setHistorico(historicos);
    }

    List<Historico> gerarExtrato(String numeroCartao){
        Cartao cartao = cartoes.get(numeroCartao);
        return cartao.getHistorico();
    }
}