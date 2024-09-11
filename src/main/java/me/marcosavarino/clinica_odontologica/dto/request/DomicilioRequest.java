package me.marcosavarino.clinica_odontologica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioRequest {
    private Integer id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
