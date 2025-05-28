package sweet.dreams.projectClinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Compra compra;

    @NotNull(message = "Informe a Quantidade do Item")
    private Integer quantidade;

    @NotNull(message = "Informe o Valor (R$) do Item")
    private Float valor;

    @NotBlank(message = "Informe a Descrição do Item")
    private String descricao;

}
