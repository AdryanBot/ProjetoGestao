package entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendas")
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_venda", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    private String nomeCliente;
    private String modeloVeiculo;
    private String marcaVeiculo; // Corrigido nome do campo
    private String precoVeiculo;

    // ===================== CONSTRUTORES =====================
    public Vendas() {
        this.dataVenda = LocalDateTime.now(); // automático com data/hora atual
    }

    public Vendas(Cliente cliente, Veiculo veiculo,
                  String nomeCliente, String modeloVeiculo, String marcaVeiculo, String precoVeiculo) {
        this.dataVenda = LocalDateTime.now(); // automático com data/hora atual
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.nomeCliente = nomeCliente;
        this.modeloVeiculo = modeloVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.precoVeiculo = precoVeiculo;
    }

    public Vendas(LocalDateTime now, Cliente cliente, Veiculo veiculo) {
    }


    // ===================== GETTERS E SETTERS =====================

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getPrecoVeiculo() {
        return precoVeiculo;
    }

    public void setPrecoVeiculo(String precoVeiculo) {
        this.precoVeiculo = precoVeiculo;
    }
}
