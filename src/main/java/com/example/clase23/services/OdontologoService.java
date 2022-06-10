package com.example.clase23.services;

import com.example.clase23.daos.IDao;
import com.example.clase23.dominio.Odontologo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> avionDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) throws SQLException {
        return odontologoDao.guardar(odontologo);
    }
    public Odontologo buscarOdontologo(int id) throws SQLException {
        return odontologoDao.buscar(id);
    }
    public List<Odontologo> buscarTodos() throws SQLException {
        return odontologoDao.buscarTodos();
    }
    public void eliminar(int id) throws SQLException {
        odontologoDao.eliminar(id);
    }
}