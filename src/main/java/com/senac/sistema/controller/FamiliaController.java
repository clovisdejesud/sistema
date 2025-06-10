
package com.senac.sistema.controller;

import com.senac.sistema.model.Familia;
import com.senac.sistema.service.FamiliaService;
import jakarta.validation.Valid;
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
@RequestMapping("/familia")
public class FamiliaController {
    
    @Autowired
    private FamiliaService familiaService;
    
       
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("familia", new Familia());
        return "familia-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Familia familia, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("familia", familia);
            return "familia/cadastro";
        }
        familiaService.salvarFamilia(familia);
        
        if (familia.getId() != null){
        return "redirect:/familia/lista";
        } else {
            return "redirect:/familia/cadastro?sucessoCadastro";
        }
    }
    
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("familias", familiaService.listarTodos());
        return "familia-listagem";
    }
        
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model  model) {
        model.addAttribute("familia", familiaService.buscarPorId(id));
        return "familia-cadastro";
    }
    
   @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        familiaService.excluir(id);
        return "redirect:/familia/lista";
    }

}
