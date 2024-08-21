package me.marcosavarino.clinica_odontologica.service;

import me.marcosavarino.clinica_odontologica.dao.Idao;
import me.marcosavarino.clinica_odontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    private Idao<Odontologo> OdontologoIdao;

    public OdontologoService(Idao<Odontologo> odontologoIdao) {
        OdontologoIdao = odontologoIdao;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return OdontologoIdao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return OdontologoIdao.buscarPorId(id);
    }
    public List<Odontologo> buscarTodosLosOdontologos(){
        return OdontologoIdao.buscarATodos();
    }
    public void odontologoUpdate(Odontologo o){
        OdontologoIdao.modificar(o);
    }
    public void odontologoDelete(Integer id){
        OdontologoIdao.eliminar(id);
    }
}
