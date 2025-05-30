package sweet.dreams.projectClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dreams.projectClinic.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
