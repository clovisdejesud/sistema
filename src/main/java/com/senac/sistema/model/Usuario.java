
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
import org.springframework.stereotype.Component;

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
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
            return this.senha;
    }

    public Role getRole() {
        return this.role;
    }
    
    @OneToOne(optional = false) // todo usuário precisa estar vinculado a um colaborador
    @JoinColumn(name = "colaborador_id", nullable = false, unique = true)
    private Colaborador colaborador;
   
}
