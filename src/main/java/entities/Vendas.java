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
    @JoinColumn(name = "carro_id", nullable = false)
    protected Carro carro;

    @ManyToOne
    @JoinColumn(name = "moto_id", nullable = false)
    protected Moto moto;

    @ManyToOne
    @JoinColumn(name = "caminhao_id", nullable = false)
    protected Caminhao caminhao;
    
}
