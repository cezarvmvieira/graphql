package br.unesp.rc.graphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endereco{
        private String enderecoId;
        private String rua;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;
        private int numero;

        public Endereco(String enderecoId, String rua, String bairro, String cidade, String estado, String cep, int numero) {
                this.enderecoId = enderecoId;
                this.rua = rua;
                this.bairro = bairro;
                this.cidade = cidade;
                this.estado = estado;
                this.cep = cep;
                this.numero = numero;
        }
}