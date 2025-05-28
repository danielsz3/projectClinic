package sweet.dreams.projectClinic.service;

import br.unialfa.compra_venda.model.Compra;
import br.unialfa.compra_venda.repository.CompraRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    @Transactional
    public void salvar(Compra compra) {
        try {
            if (compra.getItens() == null)
                throw new RuntimeException("Informe ao menos 1 item!");

            compra.getItens().forEach(itemCompra -> itemCompra.setCompra(compra));
            repository.save(compra);
        } catch (ConstraintViolationException cve) {

        } catch (Exception e) {
            throw e;

        }
    }

    public List<Compra> listarTodos() {
        return repository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }

}
