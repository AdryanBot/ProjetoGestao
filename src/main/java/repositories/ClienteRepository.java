package repositories;

import entities.Cliente; // Importa a classe Cliente da camada de entidades
import jakarta.persistence.*; // Importa as classes de JPA para trabalhar com persistência
import utils.JPAUtil; // Utilitário para obter o EntityManager

import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class ClienteRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU"); // Cria a fábrica de EntityManager com a unidade de persistência "meuPU"

    // Método para salvar um novo cliente
    public void salvar(Cliente cliente) {
        EntityManager em = emf.createEntityManager(); // Cria uma instância do EntityManager
        em.getTransaction().begin(); // Inicia a transação
        em.persist(cliente); // Persiste o cliente no banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close(); // Fecha o EntityManager após a operação
    }

    // Método para listar todos os clientes
    public List<Cliente> listar() {
        EntityManager em = emf.createEntityManager(); // Cria uma instância do EntityManager
        List<Cliente> clientes = em.createQuery("FROM Cliente", Cliente.class).getResultList(); // Executa uma consulta JPQL para listar todos os clientes
        em.close(); // Fecha o EntityManager após a consulta
        return clientes; // Retorna a lista de clientes encontrados
    }

    // Método para atualizar os dados de um cliente existente
    public void atualizar(Cliente cliente) {
        EntityManager em = emf.createEntityManager(); // Cria uma instância do EntityManager
        em.getTransaction().begin(); // Inicia a transação
        em.merge(cliente); // Atualiza os dados do cliente no banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close(); // Fecha o EntityManager após a operação
    }

    // Método para deletar um cliente
    public void deletar(Cliente cliente) {
        EntityManager em = emf.createEntityManager(); // Cria uma instância do EntityManager
        em.getTransaction().begin(); // Inicia a transação
        cliente = em.merge(cliente); // A operação de remoção exige que o cliente esteja gerenciado
        em.remove(cliente); // Remove o cliente do banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close(); // Fecha o EntityManager após a operação
    }

    // Método para contar o número total de clientes
    public Long contarCliente() {
        EntityManager em = JPAUtil.getEntityManager(); // Obtém uma instância do EntityManager utilizando JPAUtil
        Long count = em.createQuery("SELECT COUNT(c) FROM Cliente c", Long.class).getSingleResult(); // Executa uma consulta para contar o número de clientes
        em.close(); // Fecha o EntityManager após a consulta
        return count; // Retorna o número total de clientes
    }
}
