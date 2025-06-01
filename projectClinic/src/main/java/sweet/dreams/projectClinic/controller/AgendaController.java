package sweet.dreams.projectClinic.controller;

import sweet.dreams.projectClinic.model.Agenda;
import sweet.dreams.projectClinic.service.AgendaService;
import sweet.dreams.projectClinic.service.MedicoService;
import sweet.dreams.projectClinic.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarAgendas(Model model) {
        model.addAttribute("agendas", agendaService.listarTodos());
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/agendas";
    }

    @PostMapping
    public String adicionarAgenda(@ModelAttribute Agenda agenda) {
        agendaService.salvar(agenda);
        return "redirect:/agendas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Agenda agenda = agendaService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID de Agenda inválido:" + id));
        model.addAttribute("agenda", agenda);
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/editar_agenda";
    }

    @PostMapping("/editar/{id}")
    public String atualizarAgenda(@PathVariable Long id, @ModelAttribute Agenda agenda) {
        agendaService.atualizar(id, agenda);
        return "redirect:/agendas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAgenda(@PathVariable Long id) {
        agendaService.deletar(id);
        return "redirect:/agendas";
    }

    @Controller
    public static class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }
}
