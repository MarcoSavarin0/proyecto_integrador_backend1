package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.OdontologoTurnoResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.PacienteResponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.ResponsesTurno.Odontologos.TurnosOdontologoResoponseDto;
import me.marcosavarino.clinica_odontologica.dto.response.TurnoResponseDto;
import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.repository.IOdontologoRepository;
import me.marcosavarino.clinica_odontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
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
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodosLosOdontologos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void odontologoUpdate(Odontologo o) {
        odontologoRepository.save(o);
    }

    @Override
    public void odontologoDelete(Integer id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Optional<OdontologoTurnoResponseDto> buscarPorIdController(Integer id) {
        Optional<Odontologo> odontologoOpt = odontologoRepository.findById(id);
        if (odontologoOpt.isPresent()) {
            OdontologoTurnoResponseDto responseDto = mapearOdontologo(odontologoOpt.get());
            return Optional.of(responseDto);
        } else {
            return Optional.empty();
        }
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
