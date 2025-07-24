
package com.senac.sistema.controller;

import com.senac.sistema.model.Atividade;
import com.senac.sistema.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {
     @Autowired
    private AtividadeService atividadeService;
    
       
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("atividade", new Atividade());
        return "atividade-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Atividade atividade, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("atividade", atividade);
            return "atividade-cadastro";
        }
        atividadeService.salvarAtividade(atividade);
        
        if (atividade.getId() != null){
        return "redirect:/atividade/lista?sucessoAlteracao";
        } else {
            return "redirect:/atividade/cadastro?sucessoCadastro";
        }
    }
    
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("atividade", atividadeService.listarTodos());
        return "atividade-listagem";
    }
        
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model  model) {
        model.addAttribute("atividade", atividadeService.buscarPorId(id));
        return "atividade-cadastro";
    }
    
   @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        atividadeService.excluir(id);
        return "redirect:/atividade/lista";
    }

    @GetMapping("/lista-com-participantes")
    public String listarAtividadesComParticipantes(Model model) {
        model.addAttribute("atividades", atividadeService.listarTodasAtividadesComParticipantes());
        return "atividade-participantes-listagem"; // Nova página Thymeleaf
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesAtividade(@PathVariable Integer id, Model model) {
        model.addAttribute("atividade", atividadeService.buscarAtividadeComParticipantes(id));
        return "atividade-detalhes"; // Nova página Thymeleaf
    }
}
