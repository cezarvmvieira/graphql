package br.unesp.rc.graphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Conta {
    private int numero;
    private float saldo;
    private String tipo;

    public Conta(int numero, float saldo, String tipo) {
        this.numero = numero;
        this.saldo = saldo;
        this.tipo = tipo;
    }
}