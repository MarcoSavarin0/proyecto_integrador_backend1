package me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos;

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
public class TurnosOdontologoResoponseDto {
    private Integer id;
    private PacienteResponseDto pacienteResponseDto;
    private String fecha;
}
