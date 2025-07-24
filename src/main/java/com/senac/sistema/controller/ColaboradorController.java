
package com.senac.sistema.controller;

import com.senac.sistema.model.Role;
import com.senac.sistema.model.Colaborador;
import com.senac.sistema.repository.ColaboradorRepository;
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
@RequestMapping("/colaborador")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService colaboradorService;
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("colaborador", new Colaborador());
        model.addAttribute("papeis", Role.values());
        return "colaborador-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Colaborador colaborador, BindingResult result, Model model){
    if(result.hasErrors()){
        model.addAttribute("colaborador", colaborador);
        return "colaborador-cadastro";
    }

    if (colaborador.getId() != null) {
        return "redirect:/colaborador/lista?sucessoAlteracao";
    } else {
        return "redirect:/colaborador/cadastro?sucessoCadastro";
    }
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model model){
        model.addAttribute("colaborador", colaboradorService.buscarPorId(id));
        return "colaborador-cadastro";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        colaboradorService.excluir(id);
        return "redirect:/colaborador/lista";
    }
    
     @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("colaborador", colaboradorService.listarTodos());
        return "colaborador-listagem";
    }
    
    @GetMapping("/lista-com-atividades")
    public String listarColaboradoresComAtividades(Model model){
        model.addAttribute("colaboradores", colaboradorService.listarTodosColaboradoresComAtividades());
        return "colaborador-atividades-listagem";
    }
    
    
}
