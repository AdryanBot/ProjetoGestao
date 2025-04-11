package repositories;

import entities.Caminhao; // Importa a classe Caminhao da camada de entidades
import jakarta.persistence.*; // Importa as classes do JPA para persistência de dados
import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class CaminhaoRepository {

    // Cria uma fábrica de EntityManager para gerenciar as transações
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    // Método para salvar um caminhão no banco de dados
    public void salvar(Caminhao caminhao) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.persist(caminhao); // Persiste o objeto Caminhao no banco
        em.getTransaction().commit(); // Commit da transação, efetiva as mudanças no banco
        em.close(); // Fecha o EntityManager
    }

    // Método para listar todos os caminhões do banco de dados
    public List<Caminhao> listar() {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        // Executa uma consulta JPQL para listar todos os Caminhao
        List<Caminhao> caminhoes = em.createQuery("FROM Caminhao", Caminhao.class).getResultList();
        em.close(); // Fecha o EntityManager
        return caminhoes; // Retorna a lista de caminhões
    }

    // Método para atualizar um caminhão no banco de dados
    public void atualizar(Caminhao caminhao) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.merge(caminhao); // Atualiza os dados do caminhão no banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }

    // Método para deletar um caminhão do banco de dados
    public void deletar(Caminhao caminhao) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        caminhao = em.merge(caminhao); // Garante que o caminhão esteja gerenciado
        em.remove(caminhao); // Remove o caminhão do banco
        em.getTransaction().commit(); // Commit da transação
        em.close(); // Fecha o EntityManager
    }
}
