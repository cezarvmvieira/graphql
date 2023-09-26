package br.unesp.rc.graphql.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Historico {
    private Date dataCompra;
    private String itemComprado;
    private float valor;

    public Historico(Date dataCompra, String itemComprado, float valor) {
        this.dataCompra = dataCompra;
        this.itemComprado = itemComprado;
        this.valor = valor;
    }

}
