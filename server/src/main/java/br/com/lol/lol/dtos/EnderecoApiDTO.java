package br.com.lol.lol.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoApiDTO {
    @JsonProperty("cep")
    private String cep;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("localidade")
    private String cidade;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("logradouro")
    private String rua;
}
