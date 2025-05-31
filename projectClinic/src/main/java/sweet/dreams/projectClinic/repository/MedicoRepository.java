package sweet.dreams.projectClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweet.dreams.projectClinic.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
