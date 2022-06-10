package com.example.clase23.daos;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public T guardar(T t) throws SQLException;
    public T buscar(int id) throws SQLException;
    public void eliminar(int id) throws SQLException;
    public T modificar(Long id, T tModificado) throws SQLException;
    public List<T> buscarTodos() throws SQLException;

}