package services;

import entities.Veiculo;
import repositories.VeiculoRepository;

import java.util.List;

public class VeiculoService {

    // Instância do repositório VeiculoRepository para interação com o banco de dados
    private final VeiculoRepository veiculoRepository;
    
    // Instância estática para reutilização em outras partes do código
    public static VeiculoRepository veiculoRepo = new VeiculoRepository();

    // Construtor que inicializa o repositório VeiculoRepository
    public VeiculoService() {
        this.veiculoRepository = new VeiculoRepository();
    }

    // Método para listar todos os veículos cadastrados no banco de dados
    public List<Veiculo> listarTodos() {
        // Chama o método do repositório para obter todos os veículos
        return veiculoRepository.findAll();
    }

    // Método para listar veículos filtrados por tipo
    public List<Veiculo> listarPorTipo(int tipo) {
        // Chama o método do repositório para obter veículos do tipo específico
        return veiculoRepository.findByTipo(tipo);
    }

    // Método para buscar veículos pelo modelo (parcial)
    public List<Veiculo> buscarPorModelo(String termo) {
        // Chama o método do repositório para buscar veículos cujo modelo corresponde ao termo
        return veiculoRepository.buscarPorModeloParcial(termo);
    }

    // Método para buscar um veículo pelo seu ID
    public Veiculo buscarPorId(Long id) {
        // Chama o método do repositório para buscar um veículo pelo ID
        return veiculoRepository.findById(id);
    }

    // Método para remover um veículo pelo seu ID
    public void removerPorId(Long id) {
        // Chama o método do repositório para remover um veículo pelo ID
        veiculoRepository.deleteById(id);
    }

    // Método para mostrar a quantidade total de veículos cadastrados
    public void mostrarQtdVeiculos() {
        // Chama o método do repositório para contar o número total de veículos cadastrados
        Long total = veiculoRepo.contarVeiculos();
        
        // Exibe o total de veículos na saída padrão (console)
        System.out.println("Total de veículos cadastrados: " + total);
    }
}
