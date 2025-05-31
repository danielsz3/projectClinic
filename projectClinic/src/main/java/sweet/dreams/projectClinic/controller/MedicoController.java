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

    @PostMapping
    public String adicionarMedico(@ModelAttribute Medico medico) {
        medicoService.salvar(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Medico medico = medicoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Médico inválido:" + id));
        model.addAttribute("medico", medico);
        return "medico/editar_medico"; // Nome do arquivo HTML para edição
    }

}
