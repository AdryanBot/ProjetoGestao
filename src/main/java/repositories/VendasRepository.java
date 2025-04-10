package repositories;

import entities.Vendas;
import jakarta.persistence.*;
import java.util.List;

public class VendasRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar (Vendas vendas){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vendas);
        em.getTransaction().commit();
        em.close();
    }

    public List<Vendas> listar(){
        EntityManager em = emf.createEntityManager();
        List<Vendas> vendas = em.createQuery("FROM Vendas", Vendas.class).getResultList();
        em.close();
        return vendas;
    }

    public void deletar(Vendas vendas){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        vendas = em.merge(vendas); // Precisa estar gerenciado
        em.remove(vendas);
        em.getTransaction().commit();
        em.close();
    }
    
}
