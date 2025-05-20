
package com.senac.sistema.service;

import com.senac.sistema.model.Individuo;
import com.senac.sistema.repository.IndividuoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividuoService {
    
    @Autowired
    private IndividuoRepository individuoRepository;
    
    public Individuo salvar(Individuo individuo){
        return individuoRepository.save(individuo);
    }
    
    public List<Individuo> listarTodos(){
        return individuoRepository.findAll();
    }
    
    public Individuo buscarPorId(int id){
        return individuoRepository.findById(id).orElse(null);
    }
    
     public void excluir(int id) {
        individuoRepository.deleteById(id);
    }
}
