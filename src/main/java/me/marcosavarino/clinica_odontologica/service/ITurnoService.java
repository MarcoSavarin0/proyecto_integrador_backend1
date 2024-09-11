package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.dto.request.TurnoModificarDto;
import me.marcosavarino.clinica_odontologica.dto.request.TurnoRequestDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.entity.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<TurnoResponseDto> buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModificarDto TurnoModificarDto);

    void eliminarTurno(Integer id);

    List<TurnoResponseDto> buscarPorFecha(LocalDate fechaInit, LocalDate fechaLimit);
}
