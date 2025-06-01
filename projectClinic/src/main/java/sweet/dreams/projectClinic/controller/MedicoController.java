package sweet.dreams.projectClinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sweet.dreams.projectClinic.model.Medico;
import sweet.dreams.projectClinic.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private AgendaController agendaController;

    @Autowired
    private PacienteController pacienteController;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("medico", new Medico());
        return "medico/medicos";
    }

    @PostMapping
    public String adicionarMedico(@ModelAttribute Medico medico) {
        medicoService.salvar(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Medico medico = medicoService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID de Médico inválido:" + id));
        model.addAttribute("medico", medico);
        return "medico/editar_medico";
    }

    @PostMapping("/editar/{id}")
    public String atualizarMedico(@PathVariable Long id, @ModelAttribute Medico medico) {
        medico.setId(id);
        medicoService.salvar(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMedico(@PathVariable Long id) {
        medicoService.deletarPorId(id);
        return "redirect:/medicos";
    }

    @Controller
    public static class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }
}
