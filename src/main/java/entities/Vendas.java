package entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity(name="vendas")
public class Vendas {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data_venda")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Veiculo_id", nullable = false)
    protected Veiculo veiculo;

    @Column(name="forma_pagamento")
    private String formaPagamento;
    
}
