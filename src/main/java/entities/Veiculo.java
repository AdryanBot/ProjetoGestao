package entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "veiculoTipo", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(insertable = false, updatable = false) // é controlado pelo discriminador
    protected int veiculoTipo;

    @Column(nullable = false)
    protected String preco;

    @Column(nullable = false)
    protected String marcaVeiculo;

    @Column(nullable = false)
    protected String modelo;

    @Column(nullable = false)
    protected long anoModelo;

    @Column(nullable = false)
    protected String combustivel;

    @Column(nullable = false)
    protected String codigoFipe;

    @Column(nullable = false)
    protected String mesReferencia;

    @Column(nullable = false)
    protected String acronCombustivel;

    public Veiculo() {}

    public Veiculo(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String acronCombustivel) {
        this.veiculoTipo = veiculoTipo;
        this.preco = preco;
        this.marcaVeiculo = marcaVeiculo;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.combustivel = combustivel;
        this.codigoFipe = codigoFipe;
        this.mesReferencia = mesReferencia;
        this.acronCombustivel = acronCombustivel;
    }

    // Getters comuns (inclusive do ID)
    public Long getId() { return id; }
    public int getVeiculoTipo() { return veiculoTipo; }
    public String getPreco() { return preco; }
    public String getMarca() { return marcaVeiculo; }
    public String getModelo() { return modelo; }
    public long getAno() { return anoModelo; }
    public String getCombustivel() { return combustivel; }
    public String getCodigoFipe() { return codigoFipe; }
    public String getMesReferencia() { return mesReferencia; }
    public String getAcronCombustivel() { return acronCombustivel; }

    // Setters
    public void setPreco(String preco) { this.preco = preco; }
    public void setMarcaVeiculo(String marcaVeiculo) { this.marcaVeiculo = marcaVeiculo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnoModelo(long anoModelo) { this.anoModelo = anoModelo; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }
    public void setCodigoFipe(String codigoFipe) { this.codigoFipe = codigoFipe; }
    public void setMesReferencia(String mesReferencia) { this.mesReferencia = mesReferencia; }
    public void setAcronCombustivel(String acronCombustivel) { this.acronCombustivel = acronCombustivel; }
}
