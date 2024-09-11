package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.dto.request.TurnoModificarDto;
import me.marcosavarino.clinica_odontologica.dto.request.TurnoRequestDto;
import me.marcosavarino.clinica_odontologica.dto.response.OdontologoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.PacienteResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.entity.Paciente;
import me.marcosavarino.clinica_odontologica.entity.Turno;
import me.marcosavarino.clinica_odontologica.exception.BadRequestException;
import me.marcosavarino.clinica_odontologica.exception.ResourceNotFoundException;
import me.marcosavarino.clinica_odontologica.repository.ITurnoRepository;
import me.marcosavarino.clinica_odontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = new Turno();
        Turno turnoDesdeDb = null;
        TurnoResponseDto turnoARetornar = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoDesdeDb = turnoRepository.save(turno);
            turnoARetornar = mapearATurnoResponse(turnoDesdeDb);
        }
        logger.info("Turno Guardado: " + turnoARetornar);
        return turnoARetornar;
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turnoDesdeDb = turnoRepository.findById(id);
        TurnoResponseDto turnoResponseDto = null;
        if (turnoDesdeDb.isPresent()) {
            logger.info("Turno encontrado: " + turnoResponseDto);
            turnoResponseDto = mapearATurnoResponse(turnoDesdeDb.get());
        }
        return Optional.ofNullable(turnoResponseDto);
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for (Turno t : turnos) {

            TurnoResponseDto turnoAuxiliar = mapearATurnoResponse(t);
            turnosRespuesta.add(turnoAuxiliar);
        }
        logger.info("Turnos: " + turnosRespuesta);
        return turnosRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModificarDto turnoModificarDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModificarDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoModificarDto.getOdontologo_id());
        Turno turno = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno = new Turno(turnoModificarDto.getId(), paciente.get(), odontologo.get(),
                    LocalDate.parse(turnoModificarDto.getFecha()));
            turnoRepository.save(turno);
            logger.info("Turno modificado " + turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()) {
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar el turno " + id);
        }
    }

    @Override
    public List<TurnoResponseDto> buscarPorFecha(LocalDate fechaInit, LocalDate fechaLimit) {
        List<TurnoResponseDto> listaParseada = new ArrayList<>();
        List<Turno> fechas = turnoRepository.buscarPorFecha(fechaInit, fechaLimit);
        if (fechas.isEmpty()) {
            logger.warn("Arreglo vacio en la busqueda por fecha");
            return listaParseada;
        }
        for (Turno f : fechas) {
            TurnoResponseDto turnoAux = mapearATurnoResponse(f);
            listaParseada.add(turnoAux);
        }
        logger.info("Lista de turnos filtrada por fecha: " + listaParseada);
        return listaParseada;
    }

    private TurnoResponseDto mapearATurnoResponse(Turno turno) {
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }
}
