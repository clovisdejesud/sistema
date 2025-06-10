
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
        // Se houver erros, adicione o próprio objeto 'colaborador' de volta ao modelo
        // Isso permite que o formulário mantenha os dados que o usuário digitou
        model.addAttribute("colaborador", colaborador);
        // model.addAttribute("papeis", Role.values()); // <--- Remova esta linha se Colaborador não tiver Role
        // Se 'papeis' não for usado em 'colaborador-cadastro.html' para um Colaborador, remova esta linha.
        // Se 'Role' for usada no Colaborador, mantenha e importe.
        return "colaborador-cadastro"; // Retorna para a mesma página do formulário
    }

    // Não há codificação de senha para Colaborador. Apenas salve.
    colaboradorService.salvarColaborador(colaborador); // O save() do JPA lida com persistência (ID nulo) e merge (ID existente)

    // Após salvar, redirecione para a lista para que o usuário veja a alteração.
    // Ou, se for cadastro, redirecione para o formulário de cadastro com sucesso.
    if (colaborador.getId() != null) { // Se tem ID, é uma alteração
        return "redirect:/colaborador/lista?sucessoAlteracao";
    } else { // Se não tem ID, é um novo cadastro
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
}
