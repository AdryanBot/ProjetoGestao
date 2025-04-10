package repositories;

import entities.Cliente;
import entities.Moto;
import jakarta.persistence.*;
import java.util.List;

public class ClienteRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar (Cliente cliente){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public List<Cliente> listar(){
        EntityManager em = emf.createEntityManager();
        List<Cliente> cliente = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        em.close();
        return cliente;
    }

    public void atualizar(Cliente cliente){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(cliente); // Atualiza os dados
        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Cliente cliente){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        cliente = em.merge(cliente); // Precisa estar gerenciado
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }
    
}
