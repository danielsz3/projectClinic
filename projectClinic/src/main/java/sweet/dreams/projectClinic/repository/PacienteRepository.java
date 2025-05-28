package sweet.dreams.projectClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dreams.projectClinic.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
