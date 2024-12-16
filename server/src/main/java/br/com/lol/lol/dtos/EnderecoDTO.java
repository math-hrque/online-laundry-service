package br.com.lol.lol.dtos;

import br.com.lol.lol.models.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.uf = endereco.getUf();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }

    public EnderecoDTO(EnderecoApiDTO enderecoApiDTO) {
        this.cep = enderecoApiDTO.getCep();
        this.uf = enderecoApiDTO.getUf();
        this.cidade = enderecoApiDTO.getCidade();
        this.bairro = enderecoApiDTO.getBairro();
        this.rua = enderecoApiDTO.getRua();
    }
}
