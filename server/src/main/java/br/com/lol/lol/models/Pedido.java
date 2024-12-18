package br.com.lol.lol.models;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Long idPedido;

    @Column(name="numero_pedido", updatable = false, nullable = false, unique = true)
    private Long numeroPedido;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_pedido", insertable = false, updatable = false, nullable = false)
    private OffsetDateTime dataPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_pagamento", insertable = false)
    private OffsetDateTime dataPagamento;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_cliente", updatable = false, nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_situacao", nullable = false)
    private Situacao situacao;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_orcamento")
    private Orcamento orcamento;

    @OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PedidoRoupa> listaPedidoRoupas;

    public void pagar() {
        this.dataPagamento = LocalDateTime.now().atOffset(ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}
