package sweet.dreams.projectClinic.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sweet.dreams.projectClinic.model.Medico;
import sweet.dreams.projectClinic.service.MedicoService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/medico")
public class MedicoApi {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {return ResponseEntity.ok(medicoService.listarTodos());}

    @GetMapping("/{id}")
    public ResponseEntity<Medico> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Medico medico) {
        try {
            medicoService.salvar(medico);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public  ResponseEntity alterar(@RequestBody Medico medico) {
        try {
            if (medico.getId() == null) return ResponseEntity.badRequest().build();
            medicoService.salvar(medico);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            medicoService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
