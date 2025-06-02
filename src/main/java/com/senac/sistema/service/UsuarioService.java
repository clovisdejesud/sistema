
package com.senac.sistema.service;

import com.senac.sistema.model.Individuo;
import com.senac.sistema.model.Usuario;
import com.senac.sistema.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    
     public void excluir(int id) {
        usuarioRepository.deleteById(id);
    }
}
