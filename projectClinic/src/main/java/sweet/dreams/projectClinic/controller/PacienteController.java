package sweet.dreams.projectClinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sweet.dreams.projectClinic.model.Paciente;
import sweet.dreams.projectClinic.service.PacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("paciente", new Paciente()); // Para o formulário de cadastro
        return "paciente/pacientes"; // Nome do arquivo HTML (sem extensão)
    }

    @PostMapping
    public String adicionarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID de Paciente inválido:" + id));
        model.addAttribute("paciente", paciente);
        return "paciente/editar_paciente"; // Nome do arquivo HTML para edição
    }

    @PostMapping("/editar/{id}")
    public String atualizarPaciente(@PathVariable Long id, @ModelAttribute Paciente paciente) {
        paciente.setId(id); // Garante que o ID correto seja usado
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPorId(id);
        return "redirect:/pacientes";
    }
}
