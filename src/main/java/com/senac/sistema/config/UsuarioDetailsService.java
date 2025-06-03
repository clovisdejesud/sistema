
package com.senac.sistema.config;


import com.senac.sistema.model.Usuario;
import com.senac.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;


@Service
public class UsuarioDetailsService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        String role = "ROLE_" + usuario.getRole().name();
        return new User(usuario.getEmail(), usuario.getSenha(), Collections.singleton(() -> role));
    }
            
            }
