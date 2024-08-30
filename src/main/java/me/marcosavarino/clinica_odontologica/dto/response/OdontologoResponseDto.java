package me.marcosavarino.clinica_odontologica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoResponseDto {
    private Integer id;
    private String numero_De_Licencia;
    private String nombre;
    private String apellido;
}
