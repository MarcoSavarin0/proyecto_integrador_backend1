package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);

    Optional<Turno> buscarPorId(Integer id);

    List<Turno> buscarTodos();

    void modificarTurno(Turno turno);

    void eliminarTurno(Integer id);
}
