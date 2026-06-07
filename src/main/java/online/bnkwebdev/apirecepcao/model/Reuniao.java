package online.bnkwebdev.apirecepcao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "reunioes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data da reunião é obrigatória.")
    private LocalDate dataReuniao;

    @NotNull(message = "O horário da reunião é obrigatória.")
    private LocalTime horaReuniao;

    @NotBlank(message = "O nome do cliente não pode estar em branco.")
    @Size(min = 3, max = 100, message = "O nome do cliente deve ter entre 3 e 100 caracteres.")
    private String nomeCliente;

    @NotBlank(message = "O nome do arquiteto não pode estar em branco.")
    @Size(min = 3, max = 100, message = "O nome do arquiteto deve ter entre 3 e 100 caracteres.")
    private String nomeArquiteto;

    @NotBlank(message = "O nome do consultor não pode estar em branco.")
    @Size(min = 3, max = 100, message = "O nome do consultor deve ter entre 3 e 100 caracteres.")
    private String nomeConsultor;

}
