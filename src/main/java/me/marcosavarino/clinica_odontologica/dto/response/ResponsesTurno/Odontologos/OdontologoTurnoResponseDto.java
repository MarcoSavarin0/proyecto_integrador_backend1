package me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoTurnoResponseDto {
    private Integer id;
    private String numero_De_Licencia;
    private String nombre;
    private String apellido;
    private List<TurnosOdontologoResoponseDto> turnos;

    @Override
    public String toString() {
        return "OdontologoTurnoResponseDto{" +
                "id=" + id +
                ", numero_De_Licencia='" + numero_De_Licencia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", turnos=" + turnos +
                '}';
    }
}
