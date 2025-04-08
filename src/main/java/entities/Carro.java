package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name="carro")
public class Carro extends Veiculo {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="preco",nullable = false)
    protected String preco;

    @Column(name="marca", nullable = false)
    protected String marcaVeiculo;

    @Column(name="modelo", nullable = false)
    protected String modelo;

    @Column(name="ano", nullable = false)
    protected int anoModelo;

    @Column(name="combustivel", nullable = false)
    protected String combustivel;

    @Column(name="codigo_fipe", nullable = false)
    protected String codigoFipe;

    @Column(name="mes_referencia", nullable = false)
    protected String mesReferencia;

    @Column(name="acronimo_combustivel", nullable = false)
    protected String acronCombustivel;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendas> venda = new ArrayList<>();



    public Carro(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String acronCombustivel){
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
    }
    
    public void setVeiculoTipo(int veiculoTipo){
        this.veiculoTipo = veiculoTipo;
    }

    public void setPreco(String preco){
        this.preco = preco;
    }
    
    public void setMarcaVeiculo(String marcaVeiculo){
        this.marcaVeiculo = marcaVeiculo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setAnoModelo(int anoModelo){
        this.anoModelo = anoModelo;
    }

    public void setCombustivel(String combustivel){
        this.combustivel = combustivel;
    }

    public void setCodigoFipe(String codigoFipe){
        this.codigoFipe = codigoFipe;
    }

    public void setMesReferencia(String mesReferencia){
        this.mesReferencia = mesReferencia;
    }

    public void setAcronCombustivel(String acronCombustivel){
        this.acronCombustivel = acronCombustivel;
    }

    @Override
    public int getTipoVeiculo() {
        return veiculoTipo;
    }

    @Override
    public String getMarca() {
        return marcaVeiculo;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public int getAno() {
        return anoModelo;
    }

    @Override
    public String getPreco() {
        return preco;
    }

    @Override
    public String getCombustivel() {
        return combustivel;
    }

    @Override
    public String getCodigoFipe() {
        return codigoFipe;
    }

    @Override
    public String getMesReferencia() {
        return mesReferencia;
    }

    @Override
    public String getAcronCombustivel() {
        return acronCombustivel;
    }
}
