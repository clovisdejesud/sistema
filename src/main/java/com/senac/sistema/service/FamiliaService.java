
package com.senac.sistema.service;

import com.senac.sistema.model.Familia;
import com.senac.sistema.repository.FamiliaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamiliaService {
    
    @Autowired
    private FamiliaRepository familiaRepository;
    
     
    public Familia salvarFamilia(Familia familia){
        return familiaRepository.save(familia);
    }
    
    public List<Familia> listarTodos(){
        return familiaRepository.findAll();
    }
    
    public Familia buscarPorId(int id){
        return familiaRepository.findById(id).orElse(null);
    }
    
     public void excluir(int id) {
        familiaRepository.deleteById(id);
    }
}
