package repositories;

import entities.Carro; // Importa a classe Carro da camada de entidades
import jakarta.persistence.*; // Importa as classes do JPA para persistência de dados
import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class CarroRepository {

    // Cria uma fábrica de EntityManager para gerenciar as transações
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    // Método para salvar um carro no banco de dados
    public void salvar(Carro carro) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.persist(carro); // Persiste o objeto Carro no banco
        em.getTransaction().commit(); // Commit da transação, efetiva as mudanças no banco
        em.close(); // Fecha o EntityManager
    }

    // Método para listar todos os carros do banco de dados
    public List<Carro> listar() {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        // Executa uma consulta JPQL para listar todos os Carro
        List<Carro> carros = em.createQuery("FROM Carro", Carro.class).getResultList();
        em.close(); // Fecha o EntityManager
        return carros; // Retorna a lista de carros
    }

    // Método para atualizar um carro no banco de dados
    public void atualizar(Carro carro) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.merge(carro); // Atualiza os dados do carro no banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }

    // Método para deletar um carro do banco de dados
    public void deletar(Carro carro) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        carro = em.merge(carro); // Garante que o carro esteja gerenciado
        em.remove(carro); // Remove o carro do banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }
}
