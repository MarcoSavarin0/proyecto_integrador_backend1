package me.marcosavarino.clinica_odontologica.dao;

import java.util.List;

public interface Idao<T> {
    T guardar(T t);

    T buscarPorId(Integer id);

    List<T> buscarATodos();
}
