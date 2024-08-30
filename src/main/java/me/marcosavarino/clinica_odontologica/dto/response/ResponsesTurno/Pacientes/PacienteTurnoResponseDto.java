package me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Pacientes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.marcosavarino.clinica_odontologica.dto.response.DomicilioResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteTurnoResponseDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaIngreso;
    private DomicilioResponseDto domicilio;
    private List<TurnosPacienteResponseDto> turnos;
}
