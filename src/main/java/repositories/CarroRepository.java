package repositories;

import entities.Carro;
import jakarta.persistence.*;
import java.util.List;

public class CarroRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar (Carro carro){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(carro);
        em.getTransaction().commit();
        em.close();
    }

    public List<Carro> listar(){
        EntityManager em = emf.createEntityManager();
        List<Carro> carros = em.createQuery("FROM Carro", Carro.class).getResultList();
        em.close();
        return carros;
    }

    public void atualizar(Carro carro){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(carro); // Atualiza os dados
        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Carro carro){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        carro = em.merge(carro); // Precisa estar gerenciado
        em.remove(carro);
        em.getTransaction().commit();
        em.close();
    }
}
