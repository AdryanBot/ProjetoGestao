package entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity//
public class Cliente {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name="cpf",nullable = false, unique = true)
    private String cpf;

    @Column(name="data_nascimento",nullable = false)
    private String nascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendas> venda = new ArrayList<>();

    public Cliente(){

    }

    public Cliente(String nome, String cpf, String nascimento){
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getDateB() { return nascimento;}

}
