
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class Usuario {
    
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
    
    public String getEmail(){
        return email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
}
