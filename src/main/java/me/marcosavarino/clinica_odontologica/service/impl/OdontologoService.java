package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.PacienteResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.TurnosOdontologoResoponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.exception.BadRequestException;
import me.marcosavarino.clinica_odontologica.exception.ResourceNotFoundException;
import me.marcosavarino.clinica_odontologica.repository.IOdontologoRepository;
import me.marcosavarino.clinica_odontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Odontologo guardado: {}" + odontologo);
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            logger.info("Odontologo encontrado: {}" + odontologo);
            return odontologo;
        } else {
            logger.warn("No se encontro el odontologo " + id);
            throw new ResourceNotFoundException("No se encontro al odontologo con el " + id);
        }
    }

    @Override
    public List<Odontologo> buscarTodosLosOdontologos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        logger.info("Odontologos encontrado: {}" + odontologos);
        return odontologos;
    }

    @Override
    public void odontologoUpdate(Odontologo o) {
        Optional<Odontologo> ondontologo = odontologoRepository.findById(o.getId());
        if (ondontologo.isPresent()) {
            odontologoRepository.save(o);

        } else {
            logger.warn("No se encontro el odontologo " + o.getId());
            throw new ResourceNotFoundException("No se encontro al odontologo con el " + o.getId());
        }
    }

    @Override
    public void odontologoDelete(Integer id) {
        Optional<Odontologo> ondontologo = odontologoRepository.findById(id);
        if (ondontologo.isPresent()) {
            logger.info("Odontologo encontrado: {}" + ondontologo);
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar el odontologo " + id);
        }

    }

    @Override
    public Optional<OdontologoTurnoResponseDto> buscarPorIdController(Integer id) {
        Optional<Odontologo> odontologoOpt = odontologoRepository.findById(id);
        if (odontologoOpt.isPresent()) {
            OdontologoTurnoResponseDto responseDto = mapearOdontologo(odontologoOpt.get());
            return Optional.of(responseDto);
        } else {
            throw new BadRequestException("El odontologo con el " + id + " no fue encontrado");
        }
    }

    @Override
    public Optional<List<Odontologo>> buscarPorLicencia(String licencia) {
        Optional<List<Odontologo>> odnotologoMatch = odontologoRepository.buscarPorLicencia(licencia);
        logger.info("Odontologos matcheados por N de Licencia: []" + odnotologoMatch );
        return odnotologoMatch;
    }

    private OdontologoTurnoResponseDto mapearOdontologo(Odontologo odontologo) {
        OdontologoTurnoResponseDto turnoResponseDto = modelMapper.map(odontologo, OdontologoTurnoResponseDto.class);
        ModelMapper modelMapper = new ModelMapper();
        List<TurnosOdontologoResoponseDto> turnosDto = odontologo.getTurnoSet().stream().map(turno -> {
            TurnosOdontologoResoponseDto turnoResponse = modelMapper.map(turno, TurnosOdontologoResoponseDto.class);
            turnoResponse.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
            return turnoResponse;
        }).collect(Collectors.toList());
        turnoResponseDto.setTurnos(turnosDto);
        return turnoResponseDto;
    }
}
