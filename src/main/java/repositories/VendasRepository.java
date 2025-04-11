package repositories;

import entities.Vendas; // Importa a classe Vendas da camada de entidades
import jakarta.persistence.*; // Importa as classes de JPA para trabalhar com persistência
import utils.JPAUtil; // Utilitário para obter o EntityManager

import java.time.*; // Importa as classes de data e hora
import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class VendasRepository {

    // Método para salvar uma nova venda
    public void salvar(Vendas vendas) {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        em.getTransaction().begin(); // Inicia a transação
        em.persist(vendas); // Persiste a venda no banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close(); // Fecha o EntityManager após a operação
    }

    // Método para listar todas as vendas
    public List<Vendas> findAll() {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        List<Vendas> vendas = em.createQuery("FROM Vendas", Vendas.class).getResultList(); // Executa uma consulta JPQL para listar todas as vendas
        em.close(); // Fecha o EntityManager após a consulta
        return vendas; // Retorna a lista de vendas
    }

    // Método para buscar vendas por intervalo de data
    public List<Vendas> buscarPorData(LocalDateTime inicio, LocalDateTime fim) {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        TypedQuery<Vendas> query = em.createQuery("SELECT v FROM Vendas v WHERE v.dataVenda BETWEEN :inicio AND :fim", Vendas.class);
        query.setParameter("inicio", inicio); // Define o parâmetro de data de início
        query.setParameter("fim", fim); // Define o parâmetro de data de fim
        List<Vendas> resultado = query.getResultList(); // Executa a consulta e retorna o resultado
        em.close(); // Fecha o EntityManager após a consulta
        return resultado; // Retorna a lista de vendas encontradas
    }

    // Método para buscar vendas de um cliente, incluindo os veículos
    public List<Vendas> buscarVendasComVeiculoPorCliente(Long idCliente) {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        String jpql = "SELECT v FROM Vendas v JOIN v.veiculo veiculo WHERE v.cliente.id = :idCliente"; // Consulta para buscar vendas com veículos de um cliente específico
        TypedQuery<Vendas> query = em.createQuery(jpql, Vendas.class);
        query.setParameter("idCliente", idCliente); // Define o parâmetro com o id do cliente
        List<Vendas> resultado = query.getResultList(); // Executa a consulta e retorna o resultado
        em.close(); // Fecha o EntityManager após a consulta
        return resultado; // Retorna as vendas encontradas
    }

    // Método para buscar vendas de um veículo específico
    public List<Vendas> buscarVendasPorVeiculo(Long idVeiculo) {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager

        String jpql = "SELECT v FROM Vendas v JOIN v.cliente cliente WHERE v.veiculo.id = :idVeiculo"; // Consulta para buscar vendas de um veículo específico
        TypedQuery<Vendas> query = em.createQuery(jpql, Vendas.class);
        query.setParameter("idVeiculo", idVeiculo); // Define o parâmetro com o id do veículo

        List<Vendas> resultado = query.getResultList(); // Executa a consulta e retorna o resultado
        em.close(); // Fecha o EntityManager após a consulta

        return resultado; // Retorna as vendas encontradas
    }

    // Método para buscar vendas de um cliente específico
    public List<Vendas> buscarPorIdCliente(Long idCliente) {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        TypedQuery<Vendas> query = em.createQuery("SELECT v FROM Vendas v WHERE v.cliente.id = :idCliente", Vendas.class);
        query.setParameter("idCliente", idCliente); // Define o parâmetro com o id do cliente
        List<Vendas> resultado = query.getResultList(); // Executa a consulta e retorna o resultado
        em.close(); // Fecha o EntityManager após a consulta
        return resultado; // Retorna as vendas encontradas
    }

    // Método para contar o número total de vendas
    public Long contarVendas() {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        Long count = em.createQuery("SELECT COUNT(v) FROM Vendas v", Long.class).getSingleResult(); // Executa uma consulta para contar o número de vendas
        em.close(); // Fecha o EntityManager após a consulta
        return count; // Retorna o número total de vendas
    }

    // Método para somar os valores das vendas (preço dos veículos)
    public Double somarValores() {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager
        Double soma = em.createQuery(
                "SELECT SUM(CAST(REPLACE(REPLACE(v.precoVeiculo, 'R$', ''), '.', '') AS double)) FROM Vendas v", 
                Double.class
        ).getSingleResult(); // Executa uma consulta para somar os valores dos preços dos veículos
        em.close(); // Fecha o EntityManager após a consulta
        return soma; // Retorna a soma dos valores das vendas
    }
}
