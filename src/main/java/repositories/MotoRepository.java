package repositories;

import entities.Moto; // Importa a classe Moto da camada de entidades
import jakarta.persistence.*; // Importa as classes do JPA para persistência de dados
import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class MotoRepository {

    // Cria uma fábrica de EntityManager para gerenciar as transações
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    // Método para salvar uma moto no banco de dados
    public void salvar(Moto moto) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.persist(moto); // Persiste o objeto Moto no banco
        em.getTransaction().commit(); // Commit da transação, efetiva as mudanças no banco
        em.close(); // Fecha o EntityManager
    }

    // Método para listar todas as motos do banco de dados
    public List<Moto> listar() {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        // Executa uma consulta JPQL para listar todas as Motos
        List<Moto> motos = em.createQuery("FROM Moto", Moto.class).getResultList();
        em.close(); // Fecha o EntityManager
        return motos; // Retorna a lista de motos
    }

    // Método para atualizar uma moto no banco de dados
    public void atualizar(Moto moto) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.merge(moto); // Atualiza os dados da moto no banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }

    // Método para deletar uma moto do banco de dados
    public void deletar(Moto moto) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        moto = em.merge(moto); // Garante que a moto esteja gerenciada
        em.remove(moto); // Remove a moto do banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }
}
