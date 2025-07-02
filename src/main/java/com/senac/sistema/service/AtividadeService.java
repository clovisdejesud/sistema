
package com.senac.sistema.service;


import com.senac.sistema.model.Atividade;
import com.senac.sistema.repository.AtividadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {
    @Autowired
    private AtividadeRepository atividadeRepository;
    
     
    public Atividade salvarAtividade(Atividade atividade){
        return atividadeRepository.save(atividade);
    }
    
    public List<Atividade> listarTodos(){
        return atividadeRepository.findAll();
    }
    
    public Atividade buscarPorId(int id){
        return atividadeRepository.findById(id).orElse(null);
    }
    
     public void excluir(int id) {
        atividadeRepository.deleteById(id);
    }
}
