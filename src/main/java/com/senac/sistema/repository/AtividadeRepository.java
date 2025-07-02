
package com.senac.sistema.repository;

import com.senac.sistema.model.Atividade;
import com.senac.sistema.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer>{

    
}
