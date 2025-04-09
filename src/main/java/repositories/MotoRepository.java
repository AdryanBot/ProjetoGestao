package repositories;

import entities.Moto;
import jakarta.persistence.*;
import java.util.List;

public class MotoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar (Moto moto){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(moto);
        em.getTransaction().commit();
        em.close();
    }

    public List<Moto> listar(){
        EntityManager em = emf.createEntityManager();
        List<Moto> motos = em.createQuery("FROM Moto", Moto.class).getResultList();
        em.close();
        return motos;
    }

    public void atualizar(Moto moto){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(moto); // Atualiza os dados
        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Moto moto){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        moto = em.merge(moto); // Precisa estar gerenciado
        em.remove(moto);
        em.getTransaction().commit();
        em.close();
    }
}
