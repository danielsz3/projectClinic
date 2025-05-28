package sweet.dreams.projectClinic.repository;

import br.unialfa.compra_venda.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
