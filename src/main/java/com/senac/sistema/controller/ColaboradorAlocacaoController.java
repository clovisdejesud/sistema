
package com.senac.sistema.controller;


import com.senac.sistema.model.ColaboradorAlocacao;
import com.senac.sistema.model.Role;
import com.senac.sistema.repository.ColaboradorAlocacaoRepository;
import com.senac.sistema.service.AtividadeService;
import com.senac.sistema.service.ColaboradorAlocacaoService;
import com.senac.sistema.service.ColaboradorService;
import com.senac.sistema.service.IndividuoService;
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
     private IndividuoService individuoService;
    
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
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("colaboradorAlocacao", new ColaboradorAlocacao());
        model.addAttribute("papeis", Role.values());
        return "colaboradorAlocacao-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute ColaboradorAlocacao colaboradorAlocacao, BindingResult result, Model model){
    if(result.hasErrors()){
        model.addAttribute("colaborador", colaboradorAlocacao);
        return "colaboradorAlocacao-cadastro";
    }

    if (colaboradorAlocacao.getId() != null) {
        return "redirect:/colaboradorAlocacao/lista?sucessoAlteracao";
    } else {
        return "redirect:/colaboradorAlocacao/cadastro?sucessoCadastro";
    }
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model model){
        model.addAttribute("colaborador", colaboradorAlocacaoService.buscarPorId(id));
        return "colaboradorAlocacao-cadastro";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        colaboradorAlocacaoService.excluir(id);
        return "redirect:/colaboradorAlocacao/lista";
    }
    
     @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("colaboradorAlocacao", colaboradorAlocacaoService.listarTodos());
        return "colaboradorAlocacao-listagem";
    }
    
    

    
}
