package entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("3")
public class Caminhao extends Veiculo {

    public Caminhao() {
        super();
    }

    public Caminhao(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel,
                 String codigoFipe, String mesReferencia, String acronCombustivel) {
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
    }
}
