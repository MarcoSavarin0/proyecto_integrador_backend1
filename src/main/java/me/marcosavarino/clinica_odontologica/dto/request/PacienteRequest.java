package me.marcosavarino.clinica_odontologica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private DomicilioRequest domicilio;
}
