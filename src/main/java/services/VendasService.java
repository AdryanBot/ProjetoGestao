package services;

import entities.Cliente;
import entities.Vendas;
import entities.Veiculo;
import repositories.VendasRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<Vendas> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return vendaRepo.buscarPorData(inicio, fim);
    }

    public List<Vendas> buscarPorClienteId(Long idCliente) {
        return vendaRepo.buscarPorIdCliente(idCliente);
    }


    public void mostrarResumo() {
        Long total = vendaRepo.contarVendas();
        Double soma = vendaRepo.somarValores();
        System.out.println("Total de vendas: " + total);
        System.out.printf("Soma total (R$): %.2f\n", soma);
    }
}
