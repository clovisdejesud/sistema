
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
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
    private BigDecimal cargaHoraria;
    
    @NotNull
    private LocalDate dataInicio;
    
    @NotNull
    private LocalDate dataTermino;
    
    @NotBlank
    private Integer colaborador_responsavel;
    
    @NotBlank
    private String objetivo;
    
    @ManyToMany
    @JoinTable(
            name = "atividade_colaborador",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private Set<Colaborador> colaboradores = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
            name = "atividade_individuo",
            joinColumns = @JoinColumn(name ="atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "individuo_id")
            )
    private Set<Individuo> individuos = new HashSet<>();          
    
}
