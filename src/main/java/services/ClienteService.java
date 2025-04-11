package services;

import entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.ClienteRepository;
import utils.JPAUtil;

import java.util.List;

public class ClienteService {

    // Instância do repositório ClienteRepository para interação com o banco de dados
    public static ClienteRepository clienteRepo = new ClienteRepository();

    // Método para listar todos os clientes do banco de dados
    public List<Cliente> listarTodos() {
        // Obtém o EntityManager para realizar operações no banco de dados
        EntityManager em = JPAUtil.getEntityManager();
        
        // Cria uma consulta JPQL para selecionar todos os clientes
        TypedQuery<Cliente> query = em.createQuery("FROM Cliente", Cliente.class);
        
        // Executa a consulta e retorna a lista de clientes
        List<Cliente> cliente = query.getResultList();
        
        // Fecha o EntityManager após a operação
        em.close();
        
        // Retorna a lista de clientes
        return cliente;
    }

    // Método para buscar um cliente pelo seu ID
    public Cliente buscarPorId(Long id) {
        // Obtém o EntityManager para realizar operações no banco de dados
        EntityManager em = JPAUtil.getEntityManager();
        
        // Busca o cliente pelo ID
        Cliente cliente = em.find(Cliente.class, id);
        
        // Fecha o EntityManager após a operação
        em.close();
        
        // Retorna o cliente encontrado
        return cliente;
    }

    // Método para remover um cliente pelo seu ID
    public void removerPorId(Long id) {
        // Obtém o EntityManager para realizar operações no banco de dados
        EntityManager em = JPAUtil.getEntityManager();
        
        // Busca o cliente pelo ID
        Cliente cliente = em.find(Cliente.class, id);
        
        // Se o cliente for encontrado, realiza a remoção
        if (cliente != null) {
            em.getTransaction().begin(); // Inicia a transação
            em.remove(cliente); // Remove o cliente do banco
            em.getTransaction().commit(); // Comita a transação
        }
        
        // Fecha o EntityManager após a operação
        em.close();
    }

    // Método para mostrar a quantidade total de clientes cadastrados
    public void mostrarQtdCliente() {
        // Chama o método para contar o número total de clientes no banco
        Long total = clienteRepo.contarCliente();
        
        // Exibe o total de clientes na saída padrão (console)
        System.out.println("Total de clientes cadastrados: " + total);
    }
}
