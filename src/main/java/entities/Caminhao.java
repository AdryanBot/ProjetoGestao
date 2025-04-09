package entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "caminhao")
public class Caminhao extends Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "caminhao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendas> venda = new ArrayList<>();

    public Caminhao() {
        super();
    }

    public Caminhao(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel,
                    String codigoFipe, String mesReferencia, String acronCombustivel) {
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
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