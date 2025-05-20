package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name="Familia")
@Component
public class Familia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    private String nomeFamilia;
    
    @NotNull
    private Integer nrIndividuos;
    
    @NotBlank
    private String endereco;
    
    @NotBlank
    private String responsavel;
    
    
}
