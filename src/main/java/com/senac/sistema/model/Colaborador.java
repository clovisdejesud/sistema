
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name="colaborador")
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String endereco;
    
    @NotBlank
    private String telefone;
    
    @NotBlank
    private String email;
    
    @OneToOne(mappedBy = "colaborador")
    private Usuario usuario; // nem todo colaborador será um usuário
    
    @ManyToMany(mappedBy = "colaboradores")
    private Set<Atividade> atividades = new HashSet<>();
    
    public Integer getId(){
        return this.id;
    }
}
