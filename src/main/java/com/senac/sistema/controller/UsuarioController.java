
package com.senac.sistema.controller;

import com.senac.sistema.model.Role;
import com.senac.sistema.model.Usuario;
import com.senac.sistema.repository.UsuarioRepository;
import com.senac.sistema.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("papeis", Role.values());
        return "usuario-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("usuario", usuarioService.listarTodos());
            return "usuario-cadastro";
        }
        
     // Criptografar a senha aqui
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
   
        usuarioService.salvarUsuario(usuario);
        return "redirect:/usuario/cadastro?sucesso";
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "usuario-cadastro";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id){
        usuarioService.excluir(id);
        return "redirect:/usuario/lista";
    }
    
     @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("usuario", usuarioService.listarTodos());
        return "usuario-listagem";
    }
    
}
