package br.unesp.rc.graphql.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cartao {
    private String numero;
    private int numeroConta;
    private String senha;
    private String numeroDeSeguranca;
    private Date dataValidade;
    private String tipo;
    private float limite;
    private List<Historico> historico;

    public Cartao(Date dataValidade, String tipo, String senha, String numeroDeSeguranca) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digitoAleatorio = random.nextInt(10);
            stringBuilder.append(digitoAleatorio);
        }
        this.numero = stringBuilder.toString();
        this.numeroConta = -1;
        this.senha = senha;
        this.numeroDeSeguranca = numeroDeSeguranca;
        this.dataValidade = dataValidade;
        this.tipo = tipo;
        this.limite = -1;
        this.historico = new ArrayList<>();
    }

}
