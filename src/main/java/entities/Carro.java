package entities;
public class Carro extends Veiculo {
    public Carro(int veiculoTipo, String preco, String marcaVeiculo, String modelo, int anoModelo, String combustivel, String codigoFipe, String mesReferencia, String acronCombustivel){
        super(veiculoTipo, preco, marcaVeiculo, modelo, anoModelo, combustivel, codigoFipe, mesReferencia, acronCombustivel);
    }
    
    public void setVeiculoTipo(int veiculoTipo){
        this.veiculoTipo = veiculoTipo;
    }

    public void setPreco(String preco){
        this.preco = preco;
    }
    
    public void setMarcaVeiculo(String marcaVeiculo){
        this.marcaVeiculo = marcaVeiculo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setAnoModelo(int anoModelo){
        this.anoModelo = anoModelo;
    }

    public void setCombustivel(String combustivel){
        this.combustivel = combustivel;
    }

    public void setCodigoFipe(String codigoFipe){
        this.codigoFipe = codigoFipe;
    }

    public void setMesReferencia(String mesReferencia){
        this.mesReferencia = mesReferencia;
    }

    public void setAcronCombustivel(String acronCombustivel){
        this.acronCombustivel = acronCombustivel;
    }

    @Override
    public int getTipoVeiculo() {
        return veiculoTipo;
    }

    @Override
    public String getMarca() {
        return marcaVeiculo;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public int getAno() {
        return anoModelo;
    }

    @Override
    public String getPreco() {
        return preco;
    }

    @Override
    public String getCombustivel() {
        return combustivel;
    }

    @Override
    public String getCodigoFipe() {
        return codigoFipe;
    }

    @Override
    public String getMesReferencia() {
        return mesReferencia;
    }

    @Override
    public String getAcronCombustivel() {
        return acronCombustivel;
    }
}
