
package com.senac.sistema.repository;

import com.senac.sistema.model.Individuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividuoRepository extends JpaRepository<Individuo, Integer>{
    
}
