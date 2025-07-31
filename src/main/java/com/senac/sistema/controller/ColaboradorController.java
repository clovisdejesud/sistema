
package com.senac.sistema.controller;

import com.senac.sistema.model.Role;
import com.senac.sistema.model.Colaborador;
import com.senac.sistema.model.ColaboradorAlocacao;
import com.senac.sistema.repository.ColaboradorRepository;
import com.senac.sistema.service.ColaboradorAlocacaoService;
import com.senac.sistema.service.ColaboradorService;
import java.util.List;
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
    
    @Autowired
    private ColaboradorAlocacaoService colaboradorAlocacaoService;
    
    
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

    colaboradorService.salvarColaborador(colaborador);
    
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
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        return "colaborador-listagem";
    }
    
    @GetMapping("/lista-com-atividades")
    public String listarColaboradoresComAtividades(Model model) {
        // Seu código original que talvez carregasse "colaboradores"
        // Se você ainda quiser listar colaboradores (sem a alocação específica), pode manter esta linha:
        // model.addAttribute("colaboradores", colaboradorService.listarTodos()); 

        // --- ADIÇÃO NECESSÁRIA: Adicionar a lista de alocações ao Model ---
        List<ColaboradorAlocacao> alocacoes = colaboradorAlocacaoService.listarTodos();
        model.addAttribute("alocacoes", alocacoes);

        return "colaborador-atividade-listagem"; // Nome do seu arquivo HTML
    }
   
}
