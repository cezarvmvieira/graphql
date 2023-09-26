package br.unesp.rc.graphql.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartaoDTO {
    private Date dataValidade;
    private String tipo;
    private String senha;
    private String numeroDeSeguranca;

    public CartaoDTO(Date dataValidade, String tipo, String senha, String numeroDeSeguranca) {
        this.dataValidade = dataValidade;
        this.tipo = tipo;
        this.senha = senha;
        this.numeroDeSeguranca = numeroDeSeguranca;
    }

}
