package repositories;

import entities.Caminhao;
import jakarta.persistence.*;
import java.util.List;

public class CaminhaoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");

    public void salvar (Caminhao caminhao){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(caminhao);
        em.getTransaction().commit();
        em.close();
    }

    public List<Caminhao> listar(){
        EntityManager em = emf.createEntityManager();
        List<Caminhao> caminhoes = em.createQuery("FROM Caminhao", Caminhao.class).getResultList();
        em.close();
        return caminhoes;
    }

    public void atualizar(Caminhao caminhao){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(caminhao); // Atualiza os dados
        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Caminhao caminhao){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        caminhao = em.merge(caminhao); // Precisa estar gerenciado
        em.remove(caminhao);
        em.getTransaction().commit();
        em.close();
    }
}
