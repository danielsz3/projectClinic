package sweet.dreams.projectClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweet.dreams.projectClinic.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
