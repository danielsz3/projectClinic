package sweet.dreams.projectClinic.service;

import jakarta.transaction.Transactional;
import sweet.dreams.projectClinic.model.Agenda;
import sweet.dreams.projectClinic.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Transactional
    public Agenda salvar(Agenda agenda) {
        // Valida se médico e paciente existem antes de salvar
        medicoService.buscarPorId(agenda.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        pacienteService.buscarPorId(agenda.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        return agendaRepository.save(agenda);
    }

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda atualizar(Long id, Agenda agendaAtualizada) {
        return agendaRepository.findById(id).map(agenda -> {
            // Valida se médico e paciente existem antes de atualizar
            medicoService.buscarPorId(agendaAtualizada.getMedico().getId())
                    .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
            pacienteService.buscarPorId(agendaAtualizada.getPaciente().getId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

            agenda.setMedico(agendaAtualizada.getMedico());
            agenda.setPaciente(agendaAtualizada.getPaciente());
            agenda.setDataHoraAtendimento(agendaAtualizada.getDataHoraAtendimento());
            return agendaRepository.save(agenda);
        }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado!"));
    }

    public void deletar(Long id) {
        if (!agendaRepository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado!");
        }
        agendaRepository.deleteById(id);
    }
}
