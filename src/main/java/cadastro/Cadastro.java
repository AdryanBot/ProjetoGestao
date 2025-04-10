package cadastro;
import repositories.*;
import services.*;


import java.util.*;
import jakarta.persistence.*;

import entities.*;
import utils.JPAUtil;

import static utils.JPAUtil.*;

public class Cadastro {
    ArrayList <Veiculo> listaVeiculos = new ArrayList<>();
    ArrayList <Vendas> listaVendas = new ArrayList<>();
    ArrayList <Cliente> listaCliente = new ArrayList<>();

    public void adicionarVeiculo(String tipoVeiculo, String marca, String modelo, String ano, String codigoFipe, String preco, String combustivel, String acronCombustivel, String mesReferencia){
        if (tipoVeiculo.equals("cars")) {
            Carro carro = new Carro(1, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            new CarroRepository().salvar(carro);
        } else if (tipoVeiculo.equals("motorcycles")) {
            Moto moto = new Moto(2, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            new MotoRepository().salvar(moto);
        } else {
            Caminhao caminhao = new Caminhao(3, preco, marca, modelo, Integer.parseInt(ano), combustivel, codigoFipe, mesReferencia, acronCombustivel);
            new CaminhaoRepository().salvar(caminhao);
        }

        mostrarVeiculos();
    }

    public void atualizarPreco() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do veículo que deseja atualizar o preço: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // limpa o buffer

        VeiculoService veiculoServ = new VeiculoService();
        Veiculo veiculo = veiculoServ.buscarPorId(id);

        if (veiculo != null) {
            System.out.print("Digite o novo preço com as siglas (ex: R$ 99.999): ");
            String novoPreco = scanner.nextLine();

            veiculo.setPreco(novoPreco);

            EntityManager em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            em.merge(veiculo);
            em.getTransaction().commit();
            em.close();

            System.out.println("Preço atualizado com sucesso!");
        } else {
            System.out.println("Veículo com ID " + id + " não encontrado.");
        }
    }


    public void mostrarVeiculos() {
        VeiculoService vs = new VeiculoService();
        List<Veiculo> listaVeiculos = vs.listarTodos();

        if (listaVeiculos.isEmpty()) {
            System.out.println("Nenhum veiculo adicionado ainda!");
        } else {
            for (Veiculo v : listaVeiculos) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Id: " + (v.getId()));
                System.out.println("Tipo: " + (v.getVeiculoTipo() == 1 ? "Carro" : v.getVeiculoTipo() == 2 ? "Moto" : "Caminhão"));
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Ano: " + v.getAno());
                System.out.println("Preço: " + v.getPreco());
                System.out.println("Combustível: " + v.getCombustivel());
                System.out.println("Código Fipe: " + v.getCodigoFipe());
                System.out.println("Mês de Referência: " + v.getMesReferencia());
                System.out.println("Acrônimo Combustível: " + v.getAcronCombustivel());
            }
        }
    }


    public void removerVeiculo(){
        mostrarVeiculos();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do veículo que deseja remover: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());

            EntityManager em = emf.createEntityManager(); // emf é seu EntityManagerFactory
            Veiculo veiculo = em.find(Veiculo.class, id);

            if (veiculo != null) {
                em.getTransaction().begin();
                em.remove(veiculo);
                em.getTransaction().commit();
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo com ID " + id + " não encontrado.");
            }

            em.close();

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, digite um número.");
        } catch (Exception e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
        }
    }

    public void adicionarCliente(String cpf, String dataNascimento, String nomeCliente){
        Cliente cliente = new Cliente(nomeCliente, cpf, dataNascimento);
        new ClienteRepository().salvar(cliente);
        mostrarClientes();
    }

    public void mostrarClientes(){
        ClienteService vs = new ClienteService();
        List<Cliente> listaClientes = vs.listarTodos();

        if(listaClientes.isEmpty()){
            System.out.println("Nenhum cliente adicionado ainda!");
        }else{
            for(Cliente c: listaClientes){
                System.out.println("--------------------------------------------------------");
                System.out.println("Id: " + (c.getId()));
                System.out.println("Nome: " + (c.getNome()));
                System.out.println("cpf: " + (c.getCpf()));
                System.out.println("Data de nascimento: " + (c.getDateB()));
            }
        }
    }

    public void deletarCliente(){
        mostrarClientes();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do cliente que deseja remover: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());

            EntityManager em = emf.createEntityManager(); // emf é seu EntityManagerFactory
            Cliente cliente = em.find(Cliente.class, id);

            if (cliente != null) {
                em.getTransaction().begin();
                em.remove(cliente);
                em.getTransaction().commit();
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }

            em.close();

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, digite um número.");
        } catch (Exception e) {
            System.out.println("Erro ao remover Cliente: " + e.getMessage());
        }
    }

    public void adicionarVenda(){
        
    }


    public void venda(){
        Scanner scanner = new Scanner(System.in);
        String metodoPagamento = null;
        

    }

}
