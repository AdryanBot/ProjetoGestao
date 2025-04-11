package main; // Define o pacote principal da aplicação

// Importa o cliente da API da FIPE e a classe de cadastro do sistema
import api.FipeApiClient;
import controller.Cadastro;

// Importações para configurar e usar o Hibernate
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Importações para leitura do usuário e manipulação de logs
import java.util.Scanner;
import java.util.logging.LogManager;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

    public static void main(String[] args) {
        // Redireciona logs do java.util.logging para SLF4J (usado por Hibernate)
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        // Testa conexão com o banco de dados usando Hibernate
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); // Lê configuração padrão do hibernate.cfg.xml
            Session session = sessionFactory.openSession(); // Abre uma sessão de conexão com o banco
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // Fecha a sessão e a fábrica após o teste
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            // Em caso de erro, exibe mensagem e encerra o programa
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Instancia os objetos principais
        FipeApiClient fipe = new FipeApiClient(); // Cliente para acessar API FIPE
        Cadastro cadastros = new Cadastro();      // Classe que gerencia cadastros e vendas
        Scanner scanner = new Scanner(System.in); // Para ler entrada do usuário
        int op, typeV; // Variáveis de controle

        // Loop principal do menu
        do {
            // Menu principal
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
                    // Submenu de vendas
                    System.out.println("1-Nova venda");
                    System.out.println("2-Vendas realizadas");
                    op = scanner.nextInt();
                    scanner.nextLine(); // Limpa buffer do scanner

                    if(op == 1){
                        cadastros.adicionarVenda(); // Adiciona nova venda
                    } else if(op == 2){
                        cadastros.mostrarVendas(); // Mostra todas as vendas realizadas
                        System.out.println("Procurar vendas de uma data especifica?");
                        System.out.println("1-Sim");
                        System.out.println("2-Nao");
                        op = scanner.nextInt();
                        scanner.nextLine();
                        if(op == 1){
                            cadastros.vendaPorData(); // Filtra vendas por data
                        } else {
                            System.out.println("voltando ao menu...");
                        }
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                    break;

                case 2:
                    // Submenu para visualizar itens cadastrados
                    System.out.println("1-Clientes");
                    System.out.println("2-Veiculos");
                    op = scanner.nextInt();
                    scanner.nextLine();

                    if(op == 1){
                        cadastros.mostrarClientes(); // Lista todos os clientes
                        System.out.println("procurar por compras realizadas por algum cliente?");
                        System.out.println("1-Sim/2-Nao");
                        op = scanner.nextInt();

                        if(op == 1){
                            cadastros.mostrarVendasPorCliente(); // Lista vendas por cliente
                        } else {
                            System.out.println("Voltando ao menu...");
                        }
                    } else if(op == 2){
                        cadastros.mostrarVeiculos(); // Lista veículos cadastrados
                        System.out.println("--------------------------------------------------------");
                        System.out.println("1-Procurar por nome (parcial ou completo)");
                        System.out.println("2-Atualizar preco de algum dos veiculos");
                        System.out.println("3-Procurar cliente que compraram veiculos");
                        System.out.println("4-Voltar ao menu");
                        op = scanner.nextInt();
                        scanner.nextLine();

                        if(op == 1){
                            cadastros.pesquisaParcial(); // Busca por nome parcial
                        } else if(op == 2){
                            cadastros.atualizarPreco(); // Atualiza preço de veículo
                        } else if(op == 3){
                            cadastros.mostrarClientesPorVeiculo(); // Mostra compradores por veículo
                        } else {
                            System.out.println("voltando ao menu...");
                        }
                    }
                    break;

                case 3:
                    // Acesso ao submenu de cadastro
                    cadastro(scanner, fipe, cadastros);
                    break;

                case 4:
                    // Encerra o programa
                    System.out.println("Saindo...");
                    break;

                default:
                    // Tratamento para entradas inválidas
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (op != 4); // Repete até o usuário escolher sair

        scanner.close(); // Fecha o scanner ao final do programa
    }

    // Método separado para tratar cadastros (veículos e clientes)
    public static void cadastro(Scanner scanner, FipeApiClient fipe, Cadastro cadastros) {
        int typeV, IdMarca;
        String Combustivel;
        String Ano;
        String AnoECombs;
        String tipoVeiculo;

        // Exibe opções do submenu de cadastro
        System.out.println("Você entrou na função de cadastros");
        System.out.println("1-Cadastrar veículos no catálogo de vendas");
        System.out.println("2-Remover veículos do catálogo de vendas");
        System.out.println("3-Cadastrar cliente");
        System.out.println("4-Remover cliente");
        System.out.println("5-Voltar ao menu");
        int op = scanner.nextInt();

        switch (op) {
            case 1:
                // Cadastro de veículo com apoio da API FIPE
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

                // Exibe marcas disponíveis na API para o tipo de veículo
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

                // Formata ano + tipo de combustível conforme esperado pela API
                AnoECombs = Ano + "-" + Combustivel;

                // Exibe modelos disponíveis para a marca escolhida
                fipe.modelosPorMarca(tipoVeiculo, IdMarca, AnoECombs);

                System.out.println("Digite o código do modelo desejado:");
                int IdModelo = scanner.nextInt();
                scanner.nextLine();

                // Exibe os detalhes do modelo selecionado
                fipe.detalhesVeiculo(tipoVeiculo, IdMarca, IdModelo, AnoECombs);
                break;

            case 2:
                cadastros.removerVeiculo(); // Remove veículo do catálogo
                break;

            case 3:
                // Cadastro de cliente
                System.out.println("Cadastro de clientes:");
                System.out.println("Digite o nome do cliente:");
                scanner.nextLine(); // Limpa o buffer
                String nomeCliente = scanner.nextLine();

                System.out.println("Digite o cpf do cliente:");
                String cpf = scanner.nextLine();

                // Coleta data de nascimento separadamente
                System.out.println("Digite o dia em que o cliente nasceu:");
                String DDD = scanner.nextLine();
                System.out.println("Digite o mes em que o cliente nasceu:");
                String MMM = scanner.nextLine();
                System.out.println("Digite o ano em que o cliente nasceu:");
                String AAA = scanner.nextLine();
                String dataNascimento = DDD + "/" + MMM + "/" + AAA;

                // Salva o cliente
                cadastros.adicionarCliente(cpf, dataNascimento, nomeCliente);
                break;

            case 4:
                System.out.println("Remoção de cliente:");
                cadastros.deletarCliente(); // Remove cliente do sistema
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
