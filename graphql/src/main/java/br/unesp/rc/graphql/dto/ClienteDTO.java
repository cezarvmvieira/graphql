package br.unesp.rc.graphql.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;
    private String tipo;
    private String valor;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String cpf, String rua, String bairro, String cidade, String estado, String cep,
            int numero, String tipo, String valor) {
        this.nome = nome;
        this.cpf = cpf;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
        this.tipo = tipo;
        this.valor = valor;
    }

}
