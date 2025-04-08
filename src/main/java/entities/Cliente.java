package entities;

import java.time.LocalDate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity(name = " cliente")
public class Cliente {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name="cpf",nullable = false, unique = true)
    private String cpf;

    @Column(name="data_nascimento",nullable = false)
    private LocalDate nascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendas> venda = new ArrayList<>();

}
