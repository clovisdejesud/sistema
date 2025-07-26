
package com.senac.sistema.repository;

import com.senac.sistema.model.ColaboradorAlocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorAlocacaoRepository extends JpaRepository<ColaboradorAlocacao, Integer> {
 
}
