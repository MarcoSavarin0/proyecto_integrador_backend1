package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarPorId(Integer id);

    List<Odontologo> buscarTodosLosOdontologos();

    void odontologoUpdate(Odontologo o);

    void odontologoDelete(Integer id);
}
