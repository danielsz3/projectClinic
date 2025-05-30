package sweet.dreams.projectClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dreams.projectClinic.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
