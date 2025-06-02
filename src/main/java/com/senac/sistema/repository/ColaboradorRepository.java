
package com.senac.sistema.repository;

import com.senac.sistema.model.Colaborador;
import com.senac.sistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Colaborador findByEmail(String email);
}
