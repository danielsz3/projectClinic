package sweet.dreams.projectClinic.api;

import br.unialfa.compra_venda.model.Compra;
import br.unialfa.compra_venda.service.CompraService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/compra")
public class CompraApi {

    @Autowired
    private final CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> listarTodos() {
        return ResponseEntity.ok(compraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Compra compra) {
        compraService.salvar(compra);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity alterar(@RequestBody Compra compra) {
        if (compra.getId() == null)
            return ResponseEntity.badRequest().build();
        compraService.salvar(compra);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        compraService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
