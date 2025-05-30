
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="atividadeIndividuo")
public class AtividadeIndividuo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    private String nomeAtividade;
    
    @NotBlank
    private String responsavelAtividade;
    
    @NotBlank
    private String periodo; //manhã, tarde ou noite
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    
    @ManyToOne
    @JoinColumn(name = "individuo_id")
    private Individuo individuo; // um indivíduo pode estar vinculado ou não
    
}
