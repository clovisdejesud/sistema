package com.senac.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atividade")
public class Atividade_ColaboradorController {
    
     @GetMapping("/atividade-colaborador")
    public String mostrarVinculoAtividade() {
        return "atividade-colaborador";
    }
    
}
