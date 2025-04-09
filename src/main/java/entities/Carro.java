package entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Carro extends Veiculo {

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendas> venda = new ArrayList<>();

    public Carro() {
        super();
    }

    public Carro(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel,
                 String codigoFipe, String mesReferencia, String acronCombustivel) {
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
    }
}
