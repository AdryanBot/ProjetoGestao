package main;

import api.FipeApiClient;
import controller.Cadastro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;
import java.util.logging.LogManager;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        // Teste de conexão com o banco de dados usando hibernate
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // Fecha recursos após o teste
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return; // Para a execução do programa se a conexão falhar
        }

        // Programa principal
        FipeApiClient fipe = new FipeApiClient();
        Cadastro cadastros = new Cadastro();
        Scanner scanner = new Scanner(System.in);
        int op, typeV;

        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("Escolha alguma das seguintes opções:");
            System.out.println("1-Vendas");
            System.out.println("2-Itens cadastrados");
            System.out.println("3-Cadastro");
            System.out.println("4-Sair");
            System.out.println("--------------------------------------------------------");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.println("1-Nova venda");
                    System.out.println("2-Vendas realizadas");
                    op= scanner.nextInt();
                    scanner.nextLine();
                    if(op == 1){
                        cadastros.adicionarVenda();
                    }else if(op == 2){
                        cadastros.mostrarVendas();
                        System.out.println("Procurar vendas de uma data especifica?");
                        System.out.println("1-Sim");
                        System.out.println("2-Nao");
                        op = scanner.nextInt();
                        scanner.nextLine();
                        if(op ==1){
                            cadastros.vendaPorData();
                        }else{
                            System.out.println("voltando ao menu...");
                        }
                    }else{
                        System.out.println("Opcao invalida!");
                    }
                    break;
                case 2:
                    System.out.println("1-Clientes");
                    System.out.println("2-Veiculos");
                    op = scanner.nextInt();
                    scanner.nextLine();
                    if(op == 1){
                        cadastros.mostrarClientes();
                        System.out.println("procurar por compras realizadas por algum cliente?");
                        System.out.println("1-Sim/2-Nao");
                        op = scanner.nextInt();
                        if(op == 1){
                            cadastros.mostrarVendasPorCliente();
                        }else{
                            System.out.println("Voltando ao menu...");
                        }
                    }else if(op == 2){
                        cadastros.mostrarVeiculos();
                        System.out.println("1-Procurar por nome (parcial ou completo)");
                        System.out.println("2-Atualizar preco de algum dos veiculos");
                        System.out.println("3-Procurar cliente que compraram veiculos");
                        System.out.println("4-Voltar ao menu");
                        op = scanner.nextInt();
                        scanner.nextLine();
                        if(op == 1){
                            cadastros.pesquisaParcial();
                        }else if(op == 2){
                            cadastros.atualizarPreco();
                        }else if(op == 3){
                            cadastros.mostrarClientesPorVeiculo();
                        }else{
                            System.out.println("voltando ao menu...");
                        }
                    }
                    break;
                case 3:
                    cadastro(scanner, fipe, cadastros);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (op != 4);

        scanner.close();
    }

    public static void cadastro(Scanner scanner, FipeApiClient fipe, Cadastro cadastros) {
        int typeV, IdMarca;
        String Combustivel;
        String Ano;
        String AnoECombs;
        String tipoVeiculo;

        System.out.println("Você entrou na função de cadastros");
        System.out.println("1-Cadastrar veículos no catálogo de vendas");
        System.out.println("2-Remover veículos do catálogo de vendas");
        System.out.println("3-Cadastrar cliente");
        System.out.println("4-Remover cliente");
        System.out.println("5-Voltar ao menu");
        int op = scanner.nextInt();

        switch (op) {
            case 1:
                System.out.println("Qual tipo de veículo pretende cadastrar?");
                System.out.println("1-Carros");
                System.out.println("2-Motos");
                System.out.println("3-Caminhões");
                typeV = scanner.nextInt();

                if (typeV == 1) {
                    tipoVeiculo = "cars";
                } else if (typeV == 2) {
                    tipoVeiculo = "motorcycles";
                } else if (typeV == 3) {
                    tipoVeiculo = "trucks";
                } else {
                    System.out.println("Tipo de veículo inválido!");
                    System.out.println("Voltando ao menu");
                    return;
                }

                System.out.println("Buscando marcas para " + tipoVeiculo + "...");
                fipe.mostrarMarcas(tipoVeiculo);

                System.out.println("Digite o Id da marca desejada:");
                IdMarca = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o ano desejado:");
                Ano = scanner.nextLine();
                System.out.println("Digite o tipo de combustível:");
                System.out.println("'1'Para gasolina/'2'Para álcool/'3'Para diesel");
                Combustivel = scanner.nextLine();
                AnoECombs = Ano + "-" + Combustivel;

                fipe.modelosPorMarca(tipoVeiculo, IdMarca, AnoECombs);

                System.out.println("Digite o código do modelo desejado:");
                int IdModelo = scanner.nextInt();
                scanner.nextLine();

                fipe.detalhesVeiculo(tipoVeiculo, IdMarca, IdModelo, AnoECombs);

                break;
            case 2:
                cadastros.removerVeiculo();
                break;
            case 3:
                System.out.println("Cadastro de clientes:");
                System.out.println("Digite o nome do cliente:");
                scanner.nextLine();
                String nomeCliente = scanner.nextLine();
                System.out.println("Digite o cpf do cliente:");
                String cpf = scanner.nextLine();
                System.out.println("Digite o dia em que o cliente nasceu:");
                String DDD = scanner.nextLine();
                System.out.println("Digite o mes em que o cliente nasceu:");
                String MMM = scanner.nextLine();
                System.out.println("Digite o ano em que o cliente nasceu:");
                String AAA = scanner.nextLine();
                String dataNascimento = DDD+"/"+MMM+"/"+AAA;
                cadastros.adicionarCliente(cpf, dataNascimento, nomeCliente);
                break;
            case 4:
                System.out.println("Remoção de cliente:");
                cadastros.deletarCliente();
                break;
            case 5:
                System.out.println("Voltando ao menu principal...");
                return;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }
}
