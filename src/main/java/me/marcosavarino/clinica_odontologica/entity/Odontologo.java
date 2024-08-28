package me.marcosavarino.clinica_odontologica.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer Id;
    private String numero_De_Licencia;
    private String nombre;
    private String apellido;
    @OneToMany(mappedBy = "odontologo")
    @JsonManagedReference(value = "odontologo-turno")
    private Set<Turno> turnoSet;

    @Override
    public String toString() {
        return "Odontologo{" +
                "Id=" + Id +
                ", numero_De_Licencia='" + numero_De_Licencia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
