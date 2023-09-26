package br.unesp.rc.graphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contato {
    private String contatoId;
    private String tipo;
    private String valor;

    public Contato(String contatoId, String tipo, String valor) {
        this.contatoId = contatoId;
        this.tipo = tipo;
        this.valor = valor;
    }

}