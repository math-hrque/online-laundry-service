package br.com.lol.lol.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteFielDTO {
    private ClienteDTO cliente;
    private int quantidadePedidos;
    private Double receita;
}
