package services;

import entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JPAUtil;

import java.util.List;

public class ClienteService {

    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Cliente> query = em.createQuery("FROM Veiculo", Cliente.class);
        List<Cliente> cliente = query.getResultList();
        em.close();
        return cliente;
    }

    public Cliente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }

    public void removerPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
        em.close();
    }
    
}
