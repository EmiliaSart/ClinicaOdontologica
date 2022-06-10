package com.example.clase23.services;

import com.example.clase23.dominio.Odontologo;
import com.example.clase23.dominio.Paciente;

import java.util.Arrays;
import java.util.List;



public class PacienteService implements com.example.clase23.services.IPacienteService {

    private List<Paciente> pacientes = Arrays.asList(new Paciente(1,"Lowy", "Eze","eze@hotmail.com"), new Paciente(2,"Villareal","Majo", "majito@gmail.com"));
    @Override
    public List<Paciente> listaPacientes() {
        return pacientes;
    }


    @Override
    public Paciente paciente(String email) {
        Paciente paciente = null;
        for (Paciente p: pacientes
        ) {
            if(p.getEmail()==email){
                paciente = p;
            }

        }
        return paciente;
    }

}
