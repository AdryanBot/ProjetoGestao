package repositories;

import entities.Cliente;
import entities.Moto;
import entities.Vendas;
import jakarta.persistence.*;
import utils.JPAUtil;

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

    public Long contarCliente() {
        EntityManager em = JPAUtil.getEntityManager();
        Long count = em.createQuery("SELECT COUNT(c) FROM Cliente c", Long.class).getSingleResult();
        em.close();
        return count;
    }
    
}
