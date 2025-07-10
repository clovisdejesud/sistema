
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
@Entity
@Table(name="atividade")
public class Atividade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    private String nomeAtividade;
    
    @NotBlank
    private String periodo; //manhã, tarde ou noite
    
    @NotNull
    private LocalTime horaInicio;
    
    @NotNull
    private LocalTime horaFim;
    
    @NotNull
    private String cargaHoraria;
    
    @NotNull
    private LocalDate dataInicio;
    
    private LocalDate dataTermino;
    
    @NotBlank
    private String objetivo;
    
}
