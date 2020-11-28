package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cita {
    int idCita,idPaciente,idDoctor;
    String fecha,hora;

    private String database;
    private Connection connection;
    private Statement statement;

    public Cita(String fecha,String hora) throws SQLException {
        this.fecha=fecha;
        this.hora=hora;
        this.database="consultorio.db";
        this.connection= DriverManager.getConnection("jdbc:sqlite:"+database);
        this.statement=connection.createStatement();

    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Cita(int idCita, int idPaciente, int idDoctor) throws  SQLException{
        this.idCita=idCita;
        this.idPaciente=idPaciente;
        this.idDoctor=idDoctor;
        this.database="consultorio.db";
        this.connection= DriverManager.getConnection("jdbc:sqlite:"+database);
        this.statement=connection.createStatement();

    }
    public Cita () throws SQLException {
        this.database="consultorio.db";
        this.connection= DriverManager.getConnection("jdbc:sqlite:"+database);
        this.statement=connection.createStatement();

    }

    public Connection getConnection() {
        return connection;
    }

    public boolean createCita() throws SQLException {
        String query="insert into citaPivote (fecha,hora) values (?,?) ";
        PreparedStatement prepStmt = this.connection.prepareStatement(query);
        prepStmt.setString(1,fecha);
        prepStmt.setString(2,hora);
        return prepStmt.execute();
    }
    public boolean generarCita() throws  SQLException{
        String query="insert into cita (idPaciente,idDoctor,idCita) values (?,?,?)";
        PreparedStatement prepStmt = this.connection.prepareStatement(query);
        prepStmt.setInt(1,idPaciente);
        prepStmt.setInt(2,idDoctor);
        prepStmt.setInt(3,idCita);
        return prepStmt.execute();
    }

    public void listarCitas() throws SQLException {
        ResultSet reg=this.statement.executeQuery("select * from citaPivote");


        System.out.format("+-----------------+------+%n");
        System.out.format("| Citas Disponibles      |%n");
        System.out.format("+-----------------+------+%n");

        System.out.format("| ID  |  FECHA |   HORA  |%n");
        System.out.format("+-----------------+------+%n");
        while (reg.next()){
            System.out.format("| "+reg.getInt("id")+"  |  "+reg.getString("fecha")+" | "+reg.getString("hora")+" |%n");





        }

    }

}
