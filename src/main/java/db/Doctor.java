package db;

import java.sql.SQLException;

public class Doctor extends User {
    private int id;
    private  String nombre,apellidos,fechaNaciemiento,direccion,especialidad;




    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    @Override
    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Doctor(String nombre, String apellidos, String fechaNaciemiento, String direccion,String especialidad) throws  SQLException{

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNaciemiento = fechaNaciemiento;
        this.direccion = direccion;
        this.especialidad=especialidad;
    }
}
