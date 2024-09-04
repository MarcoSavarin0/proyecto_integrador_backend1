package me.marcosavarino.clinica_odontologica.dao.impl;

import me.marcosavarino.clinica_odontologica.dao.Idao;
import me.marcosavarino.clinica_odontologica.db.H2Connection;
import me.marcosavarino.clinica_odontologica.model.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements Idao<Odontologo> {
    public static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);
    public static final String INSERT = "INSERT INTO ODONTOLOGO VALUES (DEFAULT,?,?,?)";
    public static final String SELECT_ID = "SELECT * FROM ODONTOLOGO WHERE ID = ?";
    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGO";
    public static final String UPDATE = "UPDATE ODONTOLOGO SET NUMERO_MATRICULA=?," +
            "NOMBRE=?, APELLIDO=? WHERE ID=?";
    public static final String DELETE = "DELETE FROM ODONTOLOGO WHERE ID =? ";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo OdontologoNew = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumero_De_Licencia());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                OdontologoNew = new Odontologo(id, odontologo.getNumero_De_Licencia(), odontologo.getNombre(), odontologo.getApellido());
            }
            logger.info("ODONTOLOGO AGREGADO A LA BASE DE DATOS H2 " + OdontologoNew);
        } catch (Exception e) {
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
        return OdontologoNew;
    }


    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo OdontologoMatch = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer idDB = resultSet.getInt(1);
                String NUMERO_MATRICULA = resultSet.getNString(2);
                String NOMBRE = resultSet.getNString(3);
                String APELLIDO = resultSet.getNString(4);
                OdontologoMatch = new Odontologo(id, NUMERO_MATRICULA, NOMBRE, APELLIDO);
            }
            if (OdontologoMatch != null) {
                logger.info("Se encontro el siguiente Odontologo con el id: " + id + " " + OdontologoMatch);
            } else logger.info("No se encontro ningun odontologo con el id " + id);
        } catch (Exception e) {
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
        return OdontologoMatch;
    }

    @Override
    public List<Odontologo> buscarATodos() {
        List<Odontologo> allOdontologos = new ArrayList<>();
        Connection connection = null;
        Odontologo OdontologoAdd = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer idDB = resultSet.getInt(1);
                String NUMERO_MATRICULA = resultSet.getNString(2);
                String NOMBRE = resultSet.getNString(3);
                String APELLIDO = resultSet.getNString(4);
                OdontologoAdd = new Odontologo(idDB, NUMERO_MATRICULA, NOMBRE, APELLIDO);
                allOdontologos.add(OdontologoAdd);
            }
            if (!allOdontologos.isEmpty()) {
                logger.info("Se encontraron los siguientes odontologos " + "El total es de " + allOdontologos.size() + " " + allOdontologos);
            } else logger.info("No hay odontologos en la DB");
        } catch (Exception e) {
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

        return allOdontologos;
    }

    @Override
    public void modificar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, odontologo.getNumero_De_Licencia());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("Odontologo modificado " + odontologo);
        } catch (Exception e) {
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
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("odontologo con el id " + id + " eliminado");

        } catch (Exception e) {
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
    }
}

