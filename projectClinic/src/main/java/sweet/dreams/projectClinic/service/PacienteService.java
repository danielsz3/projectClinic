package sweet.dreams.projectClinic.service;


import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweet.dreams.projectClinic.model.Paciente;
import sweet.dreams.projectClinic.repository.PacienteRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    @Transactional
    public void salvar(Paciente paciente) {
        try {
            if (paciente.getNome().isBlank() || paciente.getNome().isEmpty()) throw new RuntimeException("Adicione o nome do Paciente");
            if (paciente.getCpf().isBlank() || paciente.getCpf().isEmpty()) throw new RuntimeException("Adicione o CPF do Paciente");
            if (paciente.getDataNascimento().after(new Date()) || paciente.getDataNascimento().toString().isEmpty()) throw new RuntimeException("Adicione a data de nascimento do Paciente");
            if (paciente.getTelefone().isBlank() || paciente.getTelefone().isEmpty()) throw new RuntimeException("Adicione o telefone do Paciente");

            repository.save(paciente);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarPorId(Long id) {
        try {
            if (buscarPorId(id) == null) throw new RuntimeException("Não foi possível encontrar o paciente");
            repository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
