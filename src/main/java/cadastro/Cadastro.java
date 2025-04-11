package cadastro;
import repositories.*;
import services.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            Carro carro = new Carro(1, preco, marca, modelo, ano, combustivel, codigoFipe, mesReferencia, acronCombustivel);
            new CarroRepository().salvar(carro);
        } else if (tipoVeiculo.equals("motorcycles")) {
            Moto moto = new Moto(2, preco, marca, modelo, ano, combustivel, codigoFipe, mesReferencia, acronCombustivel);
            new MotoRepository().salvar(moto);
        } else {
            Caminhao caminhao = new Caminhao(3, preco, marca, modelo, ano, combustivel, codigoFipe, mesReferencia, acronCombustivel);
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

    public void pesquisaParcial(){
        Scanner scanner = new Scanner(System.in);
        VeiculoService service = new VeiculoService();
        System.out.println("Digite o nome completo ou parcial do veiculo que deseja procurar: ");
        String pesquisa = scanner.nextLine();
        List<Veiculo> resultados = service.buscarPorModelo(pesquisa);

        for (Veiculo v : resultados) {
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


    public void mostrarVeiculos() {
        VeiculoService vs = new VeiculoService();
        List<Veiculo> listaVeiculos = vs.listarTodos();

        if (listaVeiculos.isEmpty()) {
            System.out.println("Nenhum veiculo adicionado ainda!");
        } else {
            vs.mostrarQtdVeiculos();
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
            vs.mostrarQtdCliente();
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

    public void adicionarVenda() {
        Scanner scanner = new Scanner(System.in);

        mostrarClientes();
        System.out.print("Digite o ID do cliente: ");
        Long idCliente = Long.parseLong(scanner.nextLine());

        mostrarVeiculos();
        System.out.print("Digite o ID do veículo: ");
        Long idVeiculo = Long.parseLong(scanner.nextLine());

        EntityManager em = JPAUtil.getEntityManager();

        try {
            Cliente cliente = em.find(Cliente.class, idCliente);
            Veiculo veiculo = em.find(Veiculo.class, idVeiculo);

            if (cliente == null) {
                System.out.println("Cliente com ID " + idCliente + " não encontrado.");
                return;
            }

            if (veiculo == null) {
                System.out.println("Veículo com ID " + idVeiculo + " não encontrado.");
                return;
            }

            Vendas venda = new Vendas();
            venda.setCliente(cliente);
            venda.setVeiculo(veiculo);
            venda.setModeloVeiculo(veiculo.getModelo());
            venda.setMarcaVeiculo(veiculo.getMarca());
            venda.setPrecoVeiculo(veiculo.getPreco());
            venda.setNomeCliente(cliente.getNome());

            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();

            System.out.println("Venda registrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }



    public void mostrarVendas() {
        VendasService vendaService = new VendasService();
        List<Vendas> listaVendas = VendasService.listarTodas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if (listaVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            vendaService.mostrarQuantidadeDeVendas();
            for (Vendas v : listaVendas) {
                System.out.println("--------------------------------------------------------");
                System.out.println("ID da Venda: " + v.getId());
                System.out.println("Cliente: " + v.getNomeCliente());
                System.out.println("Veículo: " + v.getModeloVeiculo() + " - " + v.getMarcaVeiculo());
                System.out.println("Preço: " + v.getPrecoVeiculo());
                System.out.println("Data: " + v.getDataVenda().format(formatter));
            }
        }
    }

    public void vendaPorData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        VendasService service = new VendasService();
        System.out.println("Data de inicio do periodo:");
        System.out.println("Dia:");
        int DiaI = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Mes:");
        int MesI = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ano:");
        int AnoI = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Data do fim do periodo:");
        System.out.println("Dia:");
        int DiaF = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Mes:");
        int MesF = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ano:");
        int AnoF = scanner.nextInt();
        scanner.nextLine();

        // Criação das datas
        LocalDateTime dataInicio = LocalDate.of(AnoI, MesI, DiaI).atStartOfDay(); // Início do dia
        LocalDateTime dataFim = LocalDate.of(AnoF, MesF, DiaF).atTime(LocalTime.MAX); // Fim do dia

        List<Vendas> vendas = service.buscarPorPeriodo(dataInicio, dataFim);

        System.out.println("Vendas entre " + dataInicio + " e " + dataFim + ":");
        for (Vendas v : vendas) {
            
                System.out.println("--------------------------------------------------------");
                System.out.println("ID da Venda: " + v.getId());
                System.out.println("Cliente: " + v.getNomeCliente());
                System.out.println("Veículo: " + v.getModeloVeiculo() + " - " + v.getMarcaVeiculo());
                System.out.println("Preço: " + v.getPrecoVeiculo());
                System.out.println("Data: " + v.getDataVenda().format(formatter));
        }
    }

    public void mostrarVendasPorCliente() {
        VendasService vendasService = new VendasService();
        vendasService.pedirIdCliente();  // Chama o método que solicita o ID do cliente e exibe as vendas
    }

    public void mostrarClientesPorVeiculo() {
        VendasService vendasService = new VendasService();
        vendasService.pedirIdVeiculo();  // Solicita o ID do veículo e exibe os clientes
    }

}
