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


    public void mostrarVeiculosVenda(){
        Scanner scanner = new Scanner(System.in);
        String metodoPagamento = null;
        int index;
        int posicao = 1;
        if(listaVeiculos.isEmpty()){
            System.out.println("Nenhum veiculo adicionado ainda!");
        }else{
            for (Veiculo v : listaVeiculos) {
                System.out.println(posicao + " - " +
                (v.getVeiculoTipo() == 1 ? "Carro" : v.getVeiculoTipo() == 2 ? "Moto" : "Caminhão") + " " +v.getMarca() + " " +v.getModelo() + " - " +v.getCombustivel() + " - " +v.getAno() + " | " +v.getPreco() + " - " +v.getCodigoFipe()+" |");
                System.out.println("--------------------------------------------------------");
                posicao++;
            }

            System.out.println("Digite o numero de posição do veículo que deseja comprar:");
            index = scanner.nextInt();
            scanner.nextLine();
            index = index - 1;
            if(index >= 0 && index<listaVeiculos.size()){
                Veiculo veiculoSelecionado = listaVeiculos.get(index);
                System.out.println("\nVocê selecionou o seguinte veículo:");
                System.out.println("Tipo: " + (veiculoSelecionado.getVeiculoTipo() == 1 ? "Carro" : veiculoSelecionado.getVeiculoTipo() == 2 ? "Moto" : "Caminhão"));
                System.out.println("Marca: " + veiculoSelecionado.getMarca());
                System.out.println("Modelo: " + veiculoSelecionado.getModelo());
                System.out.println("Ano: " + veiculoSelecionado.getAno());
                System.out.println("Preço: " + veiculoSelecionado.getPreco());
                System.out.println("Combustível: " + veiculoSelecionado.getCombustivel());
                System.out.println("Código Fipe: " + veiculoSelecionado.getCodigoFipe());

                System.out.println("\nDeseja confirmar a compra? (1-Sim / 2-Nao)");
                int confirmacao = scanner.nextInt();
                scanner.nextLine();
                if(confirmacao != 1){
                    System.out.println("Compra cancelada, voltando ao menu...");
                    return;
                }

                System.out.println("Selecione a forma de pagamento:");
                System.out.println("1 - Cartão de Crédito");
                System.out.println("2 - Cartão de Débito");
                System.out.println("3 - Pix");
                System.out.println("4 - Dinheiro");
                int formaPagamento = scanner.nextInt();
                scanner.nextLine();
                switch(formaPagamento){
                    case 1 :
                        metodoPagamento = "Cartão de Crédito";
                        break;
                    case 2 :
                        metodoPagamento = "Cartão de Débito";
                        break;
                    case 3 :
                        metodoPagamento = "Pix";
                        break;
                    case 4 :
                        metodoPagamento = "Dinheiro";
                        break;        
                    default:
                        System.out.println("Forma de Pagamento invalida");
                        break;
                }

                System.out.println("\nCompra realizada com sucesso!");
                System.out.println("Forma de pagamento escolhida: " + metodoPagamento);
                System.out.println("Obrigado por comprar conosco!\n");
                return;

            }else{
                System.out.println("Numero invalido retornando ao menu...");
                return;

            }
        }
    }

}
