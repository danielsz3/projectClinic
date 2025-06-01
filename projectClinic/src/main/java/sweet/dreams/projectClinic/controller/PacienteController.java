package sweet.dreams.projectClinic.controller;

import edu.unialfa.clinica.model.Paciente;
import edu.unialfa.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoController medicoController;

    @Autowired
    private AgendaController agendaController;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("medicos", medicoController.listarMedicos());
        model.addAttribute("agendas", agendaController.listarAgendas());
        return "paciente/pacientes";
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
        return "paciente/editar_paciente";
    }

    @PostMapping("/editar/{id}")
    public String atualizarPaciente(@PathVariable Long id, @ModelAttribute Paciente paciente) {
        paciente.setId(id);
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPaciente(@PathVariable Long id) {
        pacienteService.deletar(id);
        return "redirect:/pacientes";
    }



    @Controller
    public static class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }
}
