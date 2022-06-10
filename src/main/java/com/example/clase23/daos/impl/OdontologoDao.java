package com.example.clase23.daos.impl;

import com.example.clase23.daos.IDao;
import com.example.clase23.dominio.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDao implements IDao<Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDao.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    public OdontologoDao() {
    }


    @Override
    public Odontologo guardar(Odontologo odontologo) throws SQLException {
        logger.info("Se va a registar al odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido() + ".");
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement getLastID = connection.createStatement();
            ResultSet lastID =  getLastID.executeQuery("SELECT TOP 1 ID FROM odontologos order by id desc");
            Long newID = 1L;

            if (lastID.next()){
                newID = lastID.getLong("ID");
                newID++;
            }

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos VALUES(?,?,?,?)");
            preparedStatement.setLong(1, newID);
            preparedStatement.setLong(2, odontologo.getNumeroMatricula());
            preparedStatement.setString(3, odontologo.getNombre());
            preparedStatement.setString(4, odontologo.getApellido());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
            odontologo.setId(newID);

        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error. No pudimos registrar al odontologo.");
            throwables.printStackTrace();
        }finally {
            connection.close();
        }

        return odontologo;
    }

    @Override
    public Odontologo buscar(int id) throws SQLException {
        logger.info("Se buscara al odontologo con id: " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id, nroMatricula, nombre, apellido FROM odontologos where id = ?");
            preparedStatement.setLong(1, id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();
            //4 Obtener resultados
            while (result.next()) {
                Long idOdontologo = result.getLong("id");
                Long nroMatricula = result.getLong("nroMatricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                odontologo = new Odontologo(idOdontologo,nroMatricula,nombre,apellido);

            }
            preparedStatement.close();
        }catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error. No se encontro al odontologo seleccionado.");
            throwables.printStackTrace();
        }finally {
            connection.close();
        }

        return odontologo;



    }

    @Override
    public void eliminar(int id) throws SQLException {
        logger.info("Se eliminara al odontologo con id: " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error.No pudimos eliminar al odontologo seleccionado.");
            throwables.printStackTrace();
        }finally {
            connection.close();
        }

    }

    @Override
    public Odontologo modificar(Long id, Odontologo tModificado) throws SQLException {
        return null;
    }

    @Override
    public List<Odontologo> buscarTodos() throws SQLException {
        logger.info("Se buscaran a todos los odontologos disponibles.");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                Long idOdontologo = result.getLong("id");
                Long nroMatricula = result.getLong("nroMatricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                odontologos.add(new Odontologo(idOdontologo,nroMatricula,nombre,apellido));

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("Ocurrio un error. No encontramos odontologos disponibles.");
            throwables.printStackTrace();
        }finally {
            connection.close();
        }
        System.out.println(odontologos);
        return odontologos;

    }
}

