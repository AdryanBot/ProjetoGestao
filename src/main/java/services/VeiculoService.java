package services;

import entities.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JPAUtil;

import java.util.List;

public class VeiculoService {

    public List<Veiculo> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo", Veiculo.class);
        List<Veiculo> veiculos = query.getResultList();
        em.close();
        return veiculos;
    }

    public List<Veiculo> listarPorTipo(int tipo) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo v WHERE v.veiculoTipo = :tipo", Veiculo.class);
        query.setParameter("tipo", tipo);
        List<Veiculo> veiculos = query.getResultList();
        em.close();
        return veiculos;
    }

    public Veiculo buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Veiculo veiculo = em.find(Veiculo.class, id);
        em.close();
        return veiculo;
    }

    public void removerPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Veiculo veiculo = em.find(Veiculo.class, id);
        if (veiculo != null) {
            em.getTransaction().begin();
            em.remove(veiculo);
            em.getTransaction().commit();
        }
        em.close();
    }
}
