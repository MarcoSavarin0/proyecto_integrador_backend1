package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.dao.Idao;
import me.marcosavarino.clinica_odontologica.model.Odontologo;
import me.marcosavarino.clinica_odontologica.model.Paciente;
import me.marcosavarino.clinica_odontologica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private Idao<Turno> turnoIDao;
    private PacienteService pacienteService;
    private OdontologoService odontologService;

    public TurnoService(Idao<Turno> turnoIDao, PacienteService pacienteService, OdontologoService odontologService) {
        this.turnoIDao = turnoIDao;
        this.pacienteService = pacienteService;
        this.odontologService = odontologService;
    }

    public Turno guardarTurno(Turno turno) {
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoARetornar = null;
        if (paciente != null && odontologo != null) {
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoARetornar = turnoIDao.guardar(turno);
        }
        return turnoARetornar;
    }

    public Turno buscarPorId(Integer id) {
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> buscarTodos() {
        return turnoIDao.buscarATodos();
    }

    public void modificarTurno(Turno turno) {
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologService.buscarPorId(turno.getOdontologo().getId());

        if (paciente == null) {
            throw new IllegalArgumentException("El paciente con el ID proporcionado no existe.");
        }
        if (odontologo == null) {
            throw new IllegalArgumentException("El odontólogo con el ID proporcionado no existe.");
        }
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turnoIDao.modificar(turno);
    }

    public void eliminarTurno(Integer id) {
        turnoIDao.eliminar(id);
    }

}
