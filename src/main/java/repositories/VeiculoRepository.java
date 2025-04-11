package repositories;

import entities.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JPAUtil;

import java.util.List;

public class VeiculoRepository {

    public List<Veiculo> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo", Veiculo.class);
        List<Veiculo> veiculos = query.getResultList();
        em.close();
        return veiculos;
    }

    public List<Veiculo> findByTipo(int tipo) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Veiculo> query = em.createQuery("FROM Veiculo v WHERE v.veiculoTipo = :tipo", Veiculo.class);
        query.setParameter("tipo", tipo);
        List<Veiculo> veiculos = query.getResultList();
        em.close();
        return veiculos;
    }

    public Veiculo findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Veiculo veiculo = em.find(Veiculo.class, id);
        em.close();
        return veiculo;
    }

    public void deleteById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Veiculo veiculo = em.find(Veiculo.class, id);
        if (veiculo != null) {
            em.getTransaction().begin();
            em.remove(veiculo);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Veiculo> buscarPorModeloParcial(String termo) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Veiculo> query = em.createQuery("SELECT v FROM Veiculo v WHERE LOWER(v.modelo) LIKE LOWER(:termo)", Veiculo.class);
        query.setParameter("termo", "%" + termo + "%");
        List<Veiculo> veiculos = query.getResultList();
        em.close();
        return veiculos;
    }
    

    public Long contarVeiculos() {
        EntityManager em = JPAUtil.getEntityManager();
        Long count = em.createQuery("SELECT COUNT(v) FROM Veiculo v", Long.class).getSingleResult();
        em.close();
        return count;
    }
}