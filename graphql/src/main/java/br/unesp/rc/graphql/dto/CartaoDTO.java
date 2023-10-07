package br.unesp.rc.graphql.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartaoDTO {
    private String tipo;
    private String senha;
    private String numeroDeSeguranca;


    public CartaoDTO(String tipo, String senha, String numeroDeSeguranca) {
        this.tipo = tipo;
        this.senha = senha;
        this.numeroDeSeguranca = numeroDeSeguranca;
    }

}
