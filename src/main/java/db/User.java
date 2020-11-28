package db;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User implements UserInterface {

    private int id;
    private  String nombre,apellidos,fechaNaciemiento,direccion,especialidad;
    private String database;
    private Connection connection;
    private Statement statement;

    public User( String nombre, String apellidos, String fechaNaciemiento, String direccion) throws SQLException {


        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNaciemiento = fechaNaciemiento;
        this.direccion = direccion;
    }
    public User( String nombre, String apellidos, String fechaNaciemiento, String direccion,String especialidad) throws SQLException {


        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNaciemiento = fechaNaciemiento;
        this.direccion = direccion;
        this.especialidad=especialidad;
    }
    public User () throws SQLException {
        this.database="consultorio.db";
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        this.statement = connection.createStatement();

    }

    public Connection getConnection() {
        return connection;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean create(User user, String table) throws SQLException {
       String query="insert into paciente (nombre,apellidos,fechaNacimiento,direccion) values (?,?,?,?)";

        PreparedStatement prepStmt = this.connection.prepareStatement(query);
        prepStmt.setString(1,user.getNombre());
        prepStmt.setString(2,user.getApellidos());
        prepStmt.setString(3,user.getFechaNaciemiento());
        prepStmt.setString(4,user.getDireccion());


        return prepStmt.execute();




    }
    public boolean createDoctor(User user) throws SQLException {
        String query="insert into doctor (nombre,apellidos,fechaNacimiento,direccion,especialidad) values (?,?,?,?,?)";


        PreparedStatement prepStmt = this.connection.prepareStatement(query);
        prepStmt.setString(1,user.getNombre());
        prepStmt.setString(2,user.getApellidos());
        prepStmt.setString(3,user.getFechaNaciemiento());
        prepStmt.setString(4,user.getDireccion());
        prepStmt.setString(5,user.getEspecialidad());

        return prepStmt.execute();




    }

    @Override
    public int delete(User user, String table)  throws SQLException {
        String query="delete from "+table+"where id ="+user.getId();
        PreparedStatement prepStmt = this.connection.prepareStatement(query);
         prepStmt.setInt(1, user.getId());
        prepStmt.execute();
        return prepStmt.getUpdateCount();

    }

    @Override
    public int update(User user, String table) throws SQLException{
        String query="update"+table+" set nombre="+user.getNombre()+" apellidos="+user.getApellidos()+" fechaNacimiento="+user.getFechaNaciemiento()+" direccion="+user.getDireccion()
                +"where id="+user.getId();


        PreparedStatement prepStmt = this.connection.prepareStatement(query);
        prepStmt.setString(1, user.getNombre());
        prepStmt.setString(2,user.getApellidos());
        prepStmt.setString(3,user.getFechaNaciemiento());
        prepStmt.setString(4,user.getDireccion());
        prepStmt.execute();
        return prepStmt.getUpdateCount();
    }

    public List<Usuario> getUserByName(String nombre,String password) throws SQLException{
        ResultSet rs = this.statement.executeQuery("select * from usuario where upper(nombre)='" + nombre.toUpperCase() + "' and password='" + password.toUpperCase() + "'");
        List<Usuario> usuario = new ArrayList();
        while (rs.next()) {
            Usuario temp = new Usuario();
            temp.setIdUsuario(rs.getInt("id"));
            temp.setIdUsuario(rs.getInt("nombre"));
            temp.setIdUsuario(rs.getInt("password"));
            temp.setIdUsuario(rs.getInt("rol"));
            usuario.add(temp);
        }
        return usuario;

    }

    public void buscar(String nombreBuscar,String tabla) throws SQLException{
        int idCliente;

        System.out.format("+-----------------+----------------------------------------+%n");
        System.out.format("|              Informacion Paciente                        |%n");
        System.out.format("+-----------------+------+----------------------------------%n");

        System.out.format("|Nombre    |     Doctor asignado     |    Hora de la cita   %n");
        System.out.format("+----------+-------------------------+--------------------+ %n");


        ResultSet rs = this.statement.executeQuery("select * from paciente where upper(nombre)='" + nombreBuscar.toUpperCase() + "'");
        System.out.format("|" +rs.getString("nombre")+"       | ");

        idCliente=rs.getInt("id");
        ResultSet filasCitas=this.statement.executeQuery("select * from cita where upper(idPaciente)='"+idCliente+"'");

        ResultSet infoDoctor=this.statement.executeQuery("select * from doctor where upper(id)='"+filasCitas.getInt("idDoctor")+"'");
        System.out.format(infoDoctor.getString("nombre")+infoDoctor.getString("apellidos")+"              |");
        ResultSet citaHora=this.statement.executeQuery("select * from citaPivote where upper(id)='"+filasCitas.getInt("id")+"'");
        System.out.format(citaHora.getString("fecha")+"| \n\n\n\n\n\n");

    }

    public void buscarDoc(String nombreDoctor) throws SQLException{

        int idCliente;

        System.out.format("+-----------------+----------------------------------------+%n");
        System.out.format("|              Informacion del Doctor                      |%n");
        System.out.format("+-----------------+------+----------------------------------%n");

        System.out.format("|Nombre    |     Paciente asignado     |    Hora de la cita   %n");
        System.out.format("+----------+-------------------------+--------------------+ %n");
        ResultSet rs = this.statement.executeQuery("select * from doctor where upper(nombre)='" + nombreDoctor.toUpperCase() + "'");
        System.out.format("|" +rs.getString("nombre")+"       | ");

        idCliente=rs.getInt("id");
        ResultSet filasCitas=this.statement.executeQuery("select * from cita where upper(idDoctor)='"+idCliente+"'");

        ResultSet infoDoctor=this.statement.executeQuery("select * from paciente where upper(id)='"+filasCitas.getInt("idPaciente")+"'");
        System.out.format(infoDoctor.getString("nombre")+infoDoctor.getString("apellidos")+"              |");
        ResultSet citaHora=this.statement.executeQuery("select * from citaPivote where upper(id)='"+filasCitas.getInt("id")+"'");
        System.out.format(citaHora.getString("fecha")+"| \n\n\n\n\n");

    }


    public void listarPacientes() throws SQLException {
        ResultSet reg=this.statement.executeQuery("select * from paciente");
        System.out.format("+-----------------+------+%n");
        System.out.format("|Lista de pacientes      |%n");
        System.out.format("+-----------------+------+%n");

        System.out.format("| ID |Nombre | Apellidos |%n");
        System.out.format("+-----------------+------+%n");
        while (reg.next()){
            System.out.format("| "+reg.getInt("id")+"  |  "+reg.getString("nombre")+" | "+reg.getString("apellidos")+" |%n");





        }
    }
    public void listarDoctores() throws  SQLException{
        ResultSet reg=this.statement.executeQuery("select * from doctor");
        System.out.format("+-----------------+------+%n");
        System.out.format("| Lista de doctores      |%n");
        System.out.format("+-----------------+------+%n");

        System.out.format("|ID |Nombre |Especialidad|%n");
        System.out.format("+-----------------+------+%n");
        while (reg.next()){
            System.out.format("| "+reg.getInt("id")+"  |  "+reg.getString("nombre")+" | "+reg.getString("especialidad")+" |%n");





        }
    }
}
