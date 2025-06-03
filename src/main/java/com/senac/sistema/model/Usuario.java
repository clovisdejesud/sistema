
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
           
    @NotBlank
    private String permissao;
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToOne(optional = false) // todo usuário precisa estar vinculado a um colaborador
    @JoinColumn(name = "colaborador_id", nullable = false, unique = true)
    private Colaborador colaborador;
    
   
}
