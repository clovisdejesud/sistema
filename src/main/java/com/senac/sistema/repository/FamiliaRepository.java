package com.senac.sistema.repository;

import com.senac.sistema.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FamiliaRepository  extends JpaRepository<Familia, Integer> {
    
}
