package me.marcosavarino.clinica_odontologica.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.marcosavarino.clinica_odontologica.entity.Domicilio;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseSaveDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private DomicilioResponseDto domicilio;
}
