package sweet.dreams.projectClinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informa o nome do Médico")
    private String nome;

    @NotBlank(message = "Informa o CRM do Médico")
    private String crm;

    @NotBlank(message = "Informa especialidade do Médico")
    private String especialidade;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Agenda> agendas;

}
