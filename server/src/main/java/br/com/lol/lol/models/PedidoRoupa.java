package br.com.lol.lol.models;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pedido_roupa")
public class PedidoRoupa implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido_roupa")
    private Long idPedidoRoupa;

    @Column(name="quantidade", updatable = false, nullable = false)
    private int quantidade;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pedido", updatable = false, nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_roupa", updatable = false, nullable = false)
    private Roupa roupa;
}
