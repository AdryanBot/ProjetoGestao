package repositories;

import entities.Moto; // Importa a classe Moto da camada de entidades
import jakarta.persistence.*; // Importa as classes do JPA para persistência de dados
import java.util.List; // Importa a classe List para trabalhar com coleções de objetos

public class MotoRepository {

    // Cria uma fábrica de EntityManager para gerenciar as transações
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    // Metodo para salvar uma moto no banco de dados
    public void salvar(Moto moto) {
        // Cria um EntityManager para interagir com o banco de dados
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Inicia a transação
        em.persist(moto); // Persiste o objeto Moto no banco
        em.getTransaction().commit(); // Commit da transação, efetiva as mudanças no banco
        em.close(); // Fecha o EntityManager
    }
}
