package com.example.clase23.controller;

import com.example.clase23.dominio.Odontologo;

import com.example.clase23.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController

public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService1) {
        this.odontologoService = odontologoService1;
    }

    @GetMapping("/odontologos")
    public List<Odontologo> getOdontologos() throws SQLException {
        return odontologoService.buscarTodos();
    }
    @GetMapping("/odontologo")
    public ModelAndView getPacientePorEmail(@RequestParam(name="id", required = false, defaultValue = "1")int id) throws SQLException {
        Odontologo odontologo = odontologoService.buscarOdontologo(id);
        ModelAndView model;

        if (odontologo == null) {
            System.out.println("Odontólogo no encontrado");
            model = new ModelAndView("odontologo404");
        } else {
            System.out.println("Odontólogo: " + odontologo.getNombre());
            model = new ModelAndView("odontologoId");
            model.addObject("nombre", odontologo.getNombre());
            model.addObject("apellido", odontologo.getApellido());
        }

        return model;
    }
}


