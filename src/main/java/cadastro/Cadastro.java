package cadastro;
import repositories.CaminhaoRepository;
import repositories.CarroRepository;
import repositories.MotoRepository;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Caminhao;
import entities.Carro;
import entities.Moto;
import entities.Veiculo;

public class Cadastro {
    ArrayList <Veiculo> listaVeiculos = new ArrayList<>();

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

    public void atualizarPreco(){
        Scanner scanner = new Scanner(System.in);
        int index;
        System.out.println("Digite a posição na lista do veiculo que deseja atualizar o preço:");
            index = scanner.nextInt();
            scanner.nextLine();
            if(index >= 0 && index<listaVeiculos.size()){
                System.out.println("Digite o novo preço com as siglas:");
                String novoPreco = scanner.nextLine();
                listaVeiculos.get(index).setPreco(novoPreco);
            }else{
                System.out.println("Índice fora dos limites.");
            }
    }

    public void mostrarVeiculos(){
        if(listaVeiculos.isEmpty()){
            System.out.println("Nenhum veiculo adicionado ainda!");
        }else{
            for (Veiculo v : listaVeiculos) {
                System.out.println("Tipo: " + (v.getTipoVeiculo() == 1 ? "Carro" : v.getTipoVeiculo() == 2 ? "Moto" : "Caminhão"));
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Ano: " + v.getAno());
                System.out.println("Preço: " + v.getPreco());
                System.out.println("Combustível: " + v.getCombustivel());
                System.out.println("Código Fipe: " + v.getCodigoFipe());
                System.out.println("Mês de Referência: " + v.getMesReferencia());
                System.out.println("Acrônimo Combustível: " + v.getAcronCombustivel());
                System.out.println("--------------------------------------------------------");
            }
        }
    }

    public void removerVeiculo(){
        Scanner scanner = new Scanner(System.in);
        int index;
        if(listaVeiculos.isEmpty()){
            System.out.println("Não há nenhum veiculo adicionado para ser removido!");
        }else{
            System.out.println("Digite a posição na lista do veiculo que deseja deletar:");
            index = scanner.nextInt();
            scanner.nextLine();
            if(index >= 0 && index<listaVeiculos.size()){
                listaVeiculos.remove(index);
                System.out.println("Veiculo deletado com sucesso!");
                System.out.println("Mostrando lista de veiculos atualizada:");
                mostrarVeiculos();
            }else{
                System.out.println("Índice fora dos limites.");
            }
        }
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
                (v.getTipoVeiculo() == 1 ? "Carro" : v.getTipoVeiculo() == 2 ? "Moto" : "Caminhão") + " " +v.getMarca() + " " +v.getModelo() + " - " +v.getCombustivel() + " - " +v.getAno() + " | " +v.getPreco() + " - " +v.getCodigoFipe()+" |");
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
                System.out.println("Tipo: " + (veiculoSelecionado.getTipoVeiculo() == 1 ? "Carro" : veiculoSelecionado.getTipoVeiculo() == 2 ? "Moto" : "Caminhão"));
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
