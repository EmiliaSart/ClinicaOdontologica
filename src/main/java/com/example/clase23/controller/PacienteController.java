package com.example.clase23.controller;

import com.example.clase23.dominio.Paciente;
import com.example.clase23.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class PacienteController {

    @Autowired
    public PacienteService pacienteService;

    @GetMapping("/pacientes/todos")
    public List<Paciente> getTodos() {
        return pacienteService.listaPacientes();
    }

    @GetMapping("/paciente")
    public ModelAndView getPacientePorEmail(@RequestParam(name="email", required = false, defaultValue = "matiaslen@dh.com")String email){
        Paciente paciente = pacienteService.paciente(email);
        ModelAndView model;

        if (paciente == null) {
            System.out.println("Paciente no encontrado");
            model = new ModelAndView("email404");
        } else {
            System.out.println("Paciente: " + paciente.getNombre());
            model = new ModelAndView("email");
            model.addObject("nombre", paciente.getNombre());
            model.addObject("apellido", paciente.getApellido());
            model.addObject("matricula", paciente.getOdontologo().getNumeroMatricula());
        }

        return model;
    }
}
