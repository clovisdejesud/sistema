
package com.senac.sistema.controller;

import com.senac.sistema.model.Individuo;
import com.senac.sistema.service.FamiliaService;
import com.senac.sistema.service.IndividuoService;
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
@RequestMapping("/individuo")
public class IndividuoController {
    
    @Autowired
    private IndividuoService individuoService;
    
     @Autowired
    private FamiliaService familiaService;
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("individuo", new Individuo());
        model.addAttribute("familias", familiaService.listarTodos());
        return "individuo-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Individuo individuo, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("familias", familiaService.listarTodos());
            return "individuo-cadastro";
        }
        individuoService.salvar(individuo);
        return "redirect:/individuo/lista";
       
    }
    
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("individuos", individuoService.listarTodos());
        return "individuo-listagem";
    }
        
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model  model) {
        model.addAttribute("individuo", individuoService.buscarPorId(id));
        model.addAttribute("familias", familiaService.listarTodos());
        return "individuo-cadastro";
    }
    
   @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        individuoService.excluir(id);
        return "redirect:/individuo/lista";
    }
    
}
