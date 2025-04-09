package entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Veiculo {

    protected int veiculoTipo;

    @Column(nullable = false)
    protected String preco;

    @Column(nullable = false)
    protected String marcaVeiculo;

    @Column(nullable = false)
    protected String modelo;

    @Column(nullable = false)
    protected int anoModelo;

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

    // setters

    public void setVeiculoTipo(int veiculoTipo) { this.veiculoTipo = veiculoTipo; }
    public void setPreco(String preco) { this.preco = preco; }
    public void setMarcaVeiculo(String marcaVeiculo) { this.marcaVeiculo = marcaVeiculo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnoModelo(int anoModelo) { this.anoModelo = anoModelo; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }
    public void setCodigoFipe(String codigoFipe) { this.codigoFipe = codigoFipe; }
    public void setMesReferencia(String mesReferencia) { this.mesReferencia = mesReferencia; }
    public void setAcronCombustivel(String acronCombustivel) { this.acronCombustivel = acronCombustivel; }

    // getters abstratos

    public abstract int getTipoVeiculo();
    public abstract String getMarca();
    public abstract String getModelo();
    public abstract int getAno();
    public abstract String getPreco();
    public abstract String getCombustivel();
    public abstract String getCodigoFipe();
    public abstract String getMesReferencia();
    public abstract String getAcronCombustivel();
}
