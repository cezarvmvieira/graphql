package br.unesp.rc.graphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Historico {
    private String itemComprado;
    private float valor;


    public Historico(String itemComprado, float valor) {
        this.itemComprado = itemComprado;
        this.valor = valor;
    }
    

}
