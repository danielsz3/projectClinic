package sweet.dreams.projectClinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informa o nome do paciente")
    private String nome;

    @NotBlank(message = "Informa o CPF do paciente")
    private String cpf;

    @NotBlank(message = "Informa a data de nascimento do paciente")
    private Date dataNascimento;

    @NotBlank(message = "Informe o telefone do paciente")
    private String telefone;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agendas;

}
