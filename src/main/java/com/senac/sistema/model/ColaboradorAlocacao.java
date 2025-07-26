
package com.senac.sistema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="colaboradoralocacao")
public class ColaboradorAlocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    public Integer getId(){
        return this.id;
    }
    
    @ManyToOne
    @JoinColumn(name = "atividade_id") // FK no banco
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "colaborador_id") // FK no banco
    private Colaborador colaborador;

    private boolean responsavel;
    
}
