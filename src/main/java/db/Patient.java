package db;

import java.sql.SQLException;

public class Patient extends User {
    private int id;
    private  String nombre,apellidos,fechaNaciemiento,direccion;


    public Patient(String nombre, String apellidos, String fechaNaciemiento, String direccion, int id, String nombre1, String apellidos1, String fechaNaciemiento1, String direccion1) throws SQLException {
        super(nombre, apellidos, fechaNaciemiento, direccion);
        this.id = id;
        this.nombre = nombre1;
        this.apellidos = apellidos1;
        this.fechaNaciemiento = fechaNaciemiento1;
        this.direccion = direccion1;
    }

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

    public Patient(String nombre, String apellidos, String fechaNaciemiento, String direccion) throws SQLException {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNaciemiento = fechaNaciemiento;
        this.direccion = direccion;



    }

}
