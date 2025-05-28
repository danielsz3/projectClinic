package sweet.dreams.projectClinic.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweet.dreams.projectClinic.model.Paciente;
import sweet.dreams.projectClinic.repository.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public void salvar(Paciente paciente) {
        try {
            if (paciente.getNome().isBlank() || paciente.getNome().isEmpty()) throw new RuntimeException("Adicione o nome do Paciente");
            if (paciente.getCpf().isBlank() || paciente.getCpf().isEmpty()) throw new RuntimeException("Adicione o CPF do Paciente");
            if (paciente.getDataNascimento().isBlank() || paciente.getDataNascimento().isEmpty()) throw new RuntimeException("Adicione a data de nascimento do Paciente");
            if (paciente.getTelefone().isBlank() || paciente.getTelefone().isEmpty()) throw new RuntimeException("Adicione o telefone do Paciente");

            if (paciente.getAgendas().isEmpty()) paciente.setAgendas(new ArrayList<>());
            if (!paciente.getAgendas().isEmpty())
                paciente.getAgendas().forEach(agenda -> agenda.setPaciente(paciente));

            repository.save(paciente);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public void deletarPorId(Long id) {
        try {
            if (buscarPorId(id).getId() == null) throw new RuntimeException("Não foi possível encontrar o paciente");
            repository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
