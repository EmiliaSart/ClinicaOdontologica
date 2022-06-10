package com.example.clase23.dominio;

public class Odontologo {
    Long id;
    Long numeroMatricula;
    String nombre;
    String apellido;

    public Odontologo(Long numeroMatricula, String nombre, String apellido){
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    };

    public Odontologo(Long id, Long numeroMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(Long numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo (" +
                "id=" + id +
                ", matricula= '" + numeroMatricula.toString() + '\'' +
                ", nombre= '" + nombre + '\'' +
                ", apellido ='" + apellido +
                ')';
    }
}