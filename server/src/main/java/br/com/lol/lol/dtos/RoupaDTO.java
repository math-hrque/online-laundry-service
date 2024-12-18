package br.com.lol.lol.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoupaDTO {
    private Long idRoupa;
    private String descricao;
    private double preco;
    private int prazoDias;
}
