package me.marcosavarino.clinica_odontologica.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turnos")

public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonBackReference(value = "paciente-turno")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    @JsonBackReference(value = "odontologo-turno")
    private Odontologo odontologo;
    @NotNull
    private LocalDate fecha;


    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
