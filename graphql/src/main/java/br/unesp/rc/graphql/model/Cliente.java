package br.unesp.rc.graphql.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cliente {
    private String clienteId;
    private String nome;
    private String cpf;
    private Endereco endereco;
    private Contato contato;
    private List<Integer> contas;
    private List<String> cartoes;

    public Cliente(String clienteId, String nome, String cpf, Endereco endereco, Contato contato) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
        this.contas = new ArrayList<>();
        this.cartoes = new ArrayList<>();
    }

}
