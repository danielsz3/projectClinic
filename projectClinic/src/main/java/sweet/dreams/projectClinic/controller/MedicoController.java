package edu.unialfa.clinica.controller;

import edu.unialfa.clinica.model.Medico;
import edu.unialfa.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("medico", new Medico()); // Para o formulário de cadastro
        return "medico/medicos"; // Nome do arquivo HTML (sem extensão)
    }

   
}
