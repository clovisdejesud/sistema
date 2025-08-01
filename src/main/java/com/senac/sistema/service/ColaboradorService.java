
package com.senac.sistema.service;

import com.senac.sistema.model.Colaborador;
import com.senac.sistema.repository.ColaboradorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
   @Autowired
    private ColaboradorRepository colaboradorRepository;
    
   
    public Colaborador salvarColaborador(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }
    
    public List<Colaborador> listarTodos(){
        return colaboradorRepository.findAll();
    }
    
    public Colaborador buscarPorId(int id){
        return colaboradorRepository.findById(id).orElse(null);
    }
    
     public void excluir(int id) {
        colaboradorRepository.deleteById(id);
    }
     
    public List<Colaborador> listarTodosColaboradoresComAtividades(){
        return colaboradorRepository.findAll();
    }
    
    public Colaborador buscarColaboradorComAtividades(Integer id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
    }
}
