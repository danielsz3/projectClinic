package sweet.dreams.projectClinic.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweet.dreams.projectClinic.model.Medico;
import sweet.dreams.projectClinic.repository.MedicoRepository;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    public void salvar(Medico medico) {
        try {
            if (medico.getNome().isBlank() || medico.getNome().isEmpty()) throw new RuntimeException("Adicione o nome do Medico");
            if (medico.getCrm().isBlank() || medico.getCrm().isEmpty()) throw new RuntimeException("Adicione o CRM do Medico");
            if (medico.getEspecialidade().isBlank() || medico.getEspecialidade().isEmpty()) throw new RuntimeException("Adicione a especialidade do Medico");

            repository.save(medico);

        } catch (Exception e){
            throw e;
        }
    }

    public List<Medico> listarTodos() {return repository.findAll();}

    public Medico buscarPorId(Long id) {return repository.findById(id).get();}

    public void deletarPorId(Long id) {
        try {
            if (buscarPorId(id).getId() == null) throw new RuntimeException("Não foi possivel encontrar o paciente");
            repository.deleteById(id);
        } catch (Exception e){
            throw e;
        }
    }
}

