package services;

import entities.Cliente;
import entities.Vendas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import entities.Veiculo;
import repositories.VendasRepository;
import utils.JPAUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class VendasService {

    private static VendasRepository vendaRepo = new VendasRepository();

    public void registrarVenda(Cliente cliente, Veiculo veiculo) {
        Vendas vendas = new Vendas(LocalDateTime.now(), cliente, veiculo);
        vendaRepo.salvar(vendas);
        System.out.println("Venda registrada com sucesso.");
    }

    public static List<Vendas> listarTodas() {
        return vendaRepo.findAll();
    }

    public List<Vendas> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepo.buscarPorData(inicio, fim);
    }

    public void mostrarVendasPorCliente(Long idCliente) {
        // Chama o repositório para buscar as vendas
        List<Vendas> vendas = vendaRepo.buscarVendasComVeiculoPorCliente(idCliente);

        // Exibe as vendas
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para o cliente com ID: " + idCliente);
        } else {
            for (Vendas v : vendas) {
                System.out.println("--------------------------------------------------------");
                System.out.println("ID da Venda: " + v.getId());
                System.out.println("Modelo do Veículo: " + v.getVeiculo().getModelo());
                System.out.println("Preço do Veículo: " + v.getVeiculo().getPreco());
                System.out.println("Data da Venda: " + v.getDataVenda());
            }
        }
    }

    public void pedirIdCliente() {
        Scanner scanner = new Scanner(System.in);

        // Pede o ID do cliente
        System.out.print("Digite o ID do cliente para visualizar suas vendas: ");
        Long idCliente = scanner.nextLong();
        scanner.nextLine();  // Limpa o buffer de linha

        // Chama o método para mostrar as vendas do cliente
        mostrarVendasPorCliente(idCliente);
    }

    public void mostrarClientesPorVeiculo(Long idVeiculo) {
        List<Vendas> vendas = vendaRepo.buscarVendasPorVeiculo(idVeiculo);

        if (vendas.isEmpty()) {
            System.out.println("Nenhum cliente comprou o veículo com ID: " + idVeiculo);
        } else {
            for (Vendas v : vendas) {
                System.out.println("--------------------------------------------------------");
                System.out.println("ID da Venda: " + v.getId());
                System.out.println("Nome do Cliente: " + v.getCliente().getNome());
                System.out.println("CPF do Cliente: " + v.getCliente().getCpf());
                System.out.println("Data da Venda: " + v.getDataVenda());
            }
        }
    }

    public void pedirIdVeiculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do veículo para ver quem comprou: ");
        Long idVeiculo = scanner.nextLong();
        scanner.nextLine(); // limpa o buffer

        mostrarClientesPorVeiculo(idVeiculo);
    }

    public List<Vendas> buscarPorClienteId(Long idCliente) {
        return vendaRepo.buscarPorIdCliente(idCliente);
    }

    public void mostrarQuantidadeDeVendas() {
        Long total = vendaRepo.contarVendas();
        System.out.println("Total de vendas: " + total);
    }
    

    public void mostrarResumo() {
        Long total = vendaRepo.contarVendas();
        Double soma = vendaRepo.somarValores();
        System.out.println("Total de vendas: " + total);
        System.out.printf("Soma total (R$): %.2f\n", soma);
    }
}
