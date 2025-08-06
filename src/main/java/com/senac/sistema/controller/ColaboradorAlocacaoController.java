
package com.senac.sistema.controller;


import com.senac.sistema.model.ColaboradorAlocacao;
import com.senac.sistema.repository.ColaboradorAlocacaoRepository;
import com.senac.sistema.service.AtividadeService;
import com.senac.sistema.service.ColaboradorAlocacaoService;
import com.senac.sistema.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/colaboradorAlocacao")
public class ColaboradorAlocacaoController {
    @Autowired
    private ColaboradorAlocacaoService colaboradorAlocacaoService;
    
    @Autowired
    private ColaboradorAlocacaoRepository colaboradorAlocacaoRepository;
    
    @Autowired
     private ColaboradorService colaboradorService;
        
    @Autowired
    private AtividadeService atividadeService;
     
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/alocar")
    public String exibirPaginaAlocacao(Model model) {
        model.addAttribute("atividades", atividadeService.listarTodos());
        model.addAttribute("colaboradoresDisponiveis", colaboradorService.listarTodos());
        model.addAttribute("colaboradorAlocacao", new ColaboradorAlocacao());
        return "colaboradoralocacao"; // nome do HTML
    }
    
    @GetMapping("/alocar/{atividadeId}")
    public String exibirPaginaAlocacaoComAtividade(@PathVariable Integer atividadeId, Model model){
        var atividadeSelecionada = atividadeService.buscarPorId(atividadeId);
        
        ColaboradorAlocacao colaboradorAlocacao = new ColaboradorAlocacao();
        colaboradorAlocacao.setAtividade(atividadeSelecionada);
        
        model.addAttribute("atividades", atividadeService.listarTodos());
        model.addAttribute("colaboradoresDisponiveis", colaboradorService.listarTodos());
        model.addAttribute("colaboradorAlocacao", colaboradorAlocacao);
        
        return "colaboradoralocacao";
        
    }
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("colaboradorAlocacao", new ColaboradorAlocacao());
        //model.addAttribute("papeis", Role.values());
        model.addAttribute("atividades", atividadeService.listarTodos()); 
        model.addAttribute("colaboradoresDisponiveis", colaboradorService.listarTodos());
        return "colaboradorAlocacao-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute ColaboradorAlocacao colaboradorAlocacao, BindingResult result, Model model){
    if(result.hasErrors()){
        model.addAttribute("colaboradorAlocacao", colaboradorAlocacao);
        model.addAttribute("atividades", atividadeService.listarTodos());
        model.addAttribute("colaboradoresDisponiveis", colaboradorService.listarTodos());
        return "colaboradorAlocacao";
    }

    colaboradorAlocacaoRepository.save(colaboradorAlocacao);
    
    return "redirect:/colaborador/lista-com-atividades?alocarSucesso";
    
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model model){
        model.addAttribute("colaboradorAlocacao", colaboradorAlocacaoService.buscarPorId(id));
        model.addAttribute("atividades", atividadeService.listarTodos());
        model.addAttribute("colaboradoresDisponiveis", colaboradorService.listarTodos());
        return "colaboradorAlocacao-cadastro";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        colaboradorAlocacaoService.excluir(id);
        return "redirect:/colaboradorAlocacao/lista";
    }
    
}
