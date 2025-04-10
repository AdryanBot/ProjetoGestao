package repositories;

import entities.Vendas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class VendasRepository {

    public void salvar(Vendas vendas) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(vendas);
        em.getTransaction().commit();
        em.close();
    }

    public List<Vendas> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Vendas> vendas = em.createQuery("FROM Vendas", Vendas.class).getResultList();
        em.close();
        return vendas;
    }

    public List<Vendas> buscarPorData(LocalDate inicio, LocalDate fim) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Vendas> query = em.createQuery(
                "SELECT v FROM Vendas v WHERE v.dataVenda BETWEEN :inicio AND :fim", Vendas.class);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        List<Vendas> resultado = query.getResultList();
        em.close();
        return resultado;
    }

    public List<Vendas> buscarPorIdCliente(Long idCliente) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Vendas> query = em.createQuery(
                "SELECT v FROM Vendas v WHERE v.cliente.id = :idCliente", Vendas.class);
        query.setParameter("idCliente", idCliente);
        List<Vendas> resultado = query.getResultList();
        em.close();
        return resultado;
    }


    public Long contarVendas() {
        EntityManager em = JPAUtil.getEntityManager();
        Long count = em.createQuery("SELECT COUNT(v) FROM Vendas v", Long.class).getSingleResult();
        em.close();
        return count;
    }

    public Double somarValores() {
        EntityManager em = JPAUtil.getEntityManager();
        Double soma = em.createQuery(
                "SELECT SUM(CAST(REPLACE(REPLACE(v.precoVeiculo, 'R$', ''), '.', '') AS double)) FROM Vendas v",
                Double.class
        ).getSingleResult();
        em.close();
        return soma;
    }
}
