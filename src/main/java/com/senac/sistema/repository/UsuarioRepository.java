
package com.senac.sistema.repository;

import com.senac.sistema.model.Individuo;
import com.senac.sistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByEmail(String email);
}
