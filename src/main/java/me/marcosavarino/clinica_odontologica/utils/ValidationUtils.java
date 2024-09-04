package me.marcosavarino.clinica_odontologica.utils;

import me.marcosavarino.clinica_odontologica.entity.Domicilio;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Paciente;

public class ValidationUtils {

    public static void validatePaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El objeto Paciente no debe ser nulo");
        }

        if (paciente.getNombre() == null || paciente.getApellido() == null ||
                paciente.getDni() == null || paciente.getFechaIngreso() == null ||
                paciente.getDomicilio() == null) {
            throw new IllegalArgumentException("Los campos 'nombre', 'apellido', 'dni', 'fechaIngreso' y 'domicilio' no deben ser nulos");
        }
    }

    public static void validateDomicilio(Domicilio domicilio) {
        if (domicilio.getCalle() == null || domicilio.getNumero() <= 0 ||
                domicilio.getLocalidad() == null || domicilio.getProvincia() == null) {
            throw new IllegalArgumentException("Los campos del domicilio 'calle', 'numero', 'localidad', y 'provincia' no deben ser nulos o inválidos");
        }
    }

    public static void validateOdontologo(Odontologo odontologo) {
        if (odontologo.getApellido() == null || odontologo.getNumero_De_Licencia() == null || odontologo.getApellido() == null || odontologo == null) {
            throw new IllegalArgumentException("Los campos 'nombre', 'apellido' y numero de licencia no deben ser nulos");
        }
    }
}

