package sweet.dreams.projectClinic.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sweet.dreams.projectClinic.model.Paciente;
import sweet.dreams.projectClinic.service.PacienteService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/paciente")
public class PacienteApi {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Paciente paciente) {
        try {
            pacienteService.salvar(paciente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity alterar(@RequestBody Paciente paciente) {
        try {
            if (paciente.getId() == null) return ResponseEntity.badRequest().build();
            pacienteService.salvar(paciente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        try {
            pacienteService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
