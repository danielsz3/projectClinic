package sweet.dreams.projectClinic.api;

import lombok.AllArgsConstructor;
import sweet.dreams.projectClinic.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sweet.dreams.projectClinic.model.Agenda;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agenda")
public class AgendaApi {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<Agenda> listarTodos() {
        return agendaService.listarTodos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Agenda>> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.buscarPorId(id));

    }


    @PostMapping
    public ResponseEntity salvar(@RequestBody Agenda agenda) {
        try {
            agendaService.salvar(agenda);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity alterar(@RequestBody Agenda agenda) {
        try {
            if (agenda.getId() == null) return ResponseEntity.badRequest().build();
            agendaService.salvar(agenda);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        try {
            agendaService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}