package services;

import entities.Veiculo;
import repositories.VeiculoRepository;

import java.util.List;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    public static VeiculoRepository veiculoRepo = new VeiculoRepository();

    public VeiculoService() {
        this.veiculoRepository = new VeiculoRepository();
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> listarPorTipo(int tipo) {
        return veiculoRepository.findByTipo(tipo);
    }

    public List<Veiculo> buscarPorModelo(String termo) {
        return veiculoRepository.buscarPorModeloParcial(termo);
    }
    

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public void removerPorId(Long id) {
        veiculoRepository.deleteById(id);
    }

    public void mostrarQtdVeiculos(){
        Long total = veiculoRepo.contarVeiculos();
        System.out.println("Total de veiculos cadastrados: " + total);
    }
}