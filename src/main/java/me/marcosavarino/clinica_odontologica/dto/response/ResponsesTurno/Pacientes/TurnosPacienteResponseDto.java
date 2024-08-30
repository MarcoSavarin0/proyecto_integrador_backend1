package me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.marcosavarino.clinica_odontologica.dto.response.OdontologoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.PacienteResponseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnosPacienteResponseDto {
    private Integer id;
    private OdontologoResponseDto odontologoResponseDto;
    private String fecha;
}
