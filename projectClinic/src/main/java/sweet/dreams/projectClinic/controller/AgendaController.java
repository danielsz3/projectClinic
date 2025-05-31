package edu.unialfa.clinica.controller;

import edu.unialfa.clinica.model.Agenda;
import edu.unialfa.clinica.service.AgendaService;
import edu.unialfa.clinica.service.MedicoService;
import edu.unialfa.clinica.service.PacienteService;
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
        model.addAttribute("agenda", new Agenda()); // Para o formulário de cadastro
        model.addAttribute("medicos", medicoService.listarTodos()); // Para o dropdown
        model.addAttribute("pacientes", pacienteService.listarTodos()); // Para o dropdown
        return "agenda/agendas"; // Nome do arquivo HTML (sem extensão)
    }


    @PostMapping
    public String adicionarAgenda(@ModelAttribute Agenda agenda) {
        // As IDs de médico e paciente virão do formulário
        // O service já valida a existência deles
        agendaService.salvar(agenda);
        return "redirect:/agendas";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Agenda agenda = agendaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Agenda inválido:" + id));
        model.addAttribute("agenda", agenda);
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda/editar_agenda"; // Nome do arquivo HTML para edição
    }

    @PostMapping("/editar/{id}")
    public String atualizarAgenda(@PathVariable Long id, @ModelAttribute Agenda agenda) {
        // O service lida com a busca e atualização
        agendaService.atualizar(id, agenda);
        return "redirect:/agendas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAgenda(@PathVariable Long id) {
        agendaService.deletar(id);
        return "redirect:/agendas";
    }

    // Controller para a página Index
    @Controller
    public static class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }
}
