package me.marcosavarino.clinica_odontologica.dao.impl;

import me.marcosavarino.clinica_odontologica.dao.Idao;
import me.marcosavarino.clinica_odontologica.db.H2Connection;
import me.marcosavarino.clinica_odontologica.model.Domicilio;
import me.marcosavarino.clinica_odontologica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PacienteDaoH2 implements Idao<Paciente> {
    public static final Logger logger = LoggerFactory.getLogger(PacienteDaoH2.class);
    public static final String INSERT = "INSERT INTO PACIENTES VALUES (DEFAULT,?,?,?,?,?)";
    public static final String SELECT_ID ="SELECT * FROM PACIENTES WHERE ID = ?";
    public DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        Paciente pacienteARetornar = null;
        Domicilio domicilioAuxiliar = domicilioDaoH2.guardar(paciente.getDomicilio());
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, domicilioAuxiliar.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                Integer id = resultSet.getInt(1);
                pacienteARetornar = new Paciente(id, paciente.getApellido(), paciente.getNombre(),
                        paciente.getDni(), paciente.getFechaIngreso(), domicilioAuxiliar);
            }
            logger.info("paciente persistido "+  pacienteARetornar);

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return pacienteARetornar;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Connection connection = null;
        Paciente pacienteEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                String apellido = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String dni = resultSet.getString(4);
                LocalDate fecha = resultSet.getDate(5).toLocalDate();
                Integer id_domicilio = resultSet.getInt(6);
                Domicilio domicilio = domicilioDaoH2.buscarPorId(id_domicilio);
                pacienteEncontrado = new Paciente(idDB, apellido, nombre, dni, fecha, domicilio);
            }
            if(pacienteEncontrado != null){
                logger.info("paciente encontrado "+ pacienteEncontrado);
            }else logger.info("paciente no encontrado");

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return pacienteEncontrado;
    }

    @Override
    public List<Paciente> buscarATodos() {
        return List.of();
    }

}
