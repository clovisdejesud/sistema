
package com.senac.sistema.service;

import com.senac.sistema.model.Colaborador;
import com.senac.sistema.model.ColaboradorAlocacao;
import com.senac.sistema.repository.ColaboradorAlocacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorAlocacaoService {
    @Autowired
    private ColaboradorAlocacaoRepository colaboradorAlocacaoRepository;
    
   
    public ColaboradorAlocacao salvarColaborador(ColaboradorAlocacao colaboradorAlocacao){
        return colaboradorAlocacaoRepository.save(colaboradorAlocacao);
    }
    
    public List<ColaboradorAlocacao> listarTodos(){
        return colaboradorAlocacaoRepository.findAll();
    }
    
    public ColaboradorAlocacao buscarPorId(int id){
        return colaboradorAlocacaoRepository.findById(id).orElse(null);
    }
    
     public void excluir(Integer id) {
        colaboradorAlocacaoRepository.deleteById(id);
    }
     
    public ColaboradorAlocacao buscarAlocacaoPorId(Integer id){
        return colaboradorAlocacaoRepository.findById(id).orElse(null);
    }

     
    public List<ColaboradorAlocacao> listarTodosColaboradoresComAtividades(){
        return colaboradorAlocacaoRepository.findAll();
    }
    
 }
