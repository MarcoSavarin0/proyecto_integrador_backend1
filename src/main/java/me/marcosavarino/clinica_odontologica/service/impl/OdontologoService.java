package me.marcosavarino.clinica_odontologica.service.impl;

import me.marcosavarino.clinica_odontologica.entity.Odontologo;
import me.marcosavarino.clinica_odontologica.repository.IDomicilioRepository;
import me.marcosavarino.clinica_odontologica.repository.IOdontologoRepository;
import me.marcosavarino.clinica_odontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;

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

}
