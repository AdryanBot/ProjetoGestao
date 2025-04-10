package services;

import entities.Vendas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JPAUtil;

import java.util.List;

public class VendasService {

    public List<Vendas> listarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Vendas> query = em.createQuery("FROM Vendas", Vendas.class);
        List<Vendas> vendas = query.getResultList();
        em.close();
        return vendas;
    }
    
}
