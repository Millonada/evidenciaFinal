package db;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ConsultorioAdmin {

    static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
// nombre de las tablas
        String TABLA_PACIENTES="paciente";
        String TABLA_DOCTORES="doctor";
        String TABLA_CITA="cita";

        String nombreBuscar;

        int seleccion;
        String user = "";
        String password = "";
        //BaseDatos persist = new BaseDatos("consultorio.db");
        User persist = new User();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese su usuario y contraseña para iniciar");
            System.out.println("Usuario:");
            user = scanner.nextLine();
            System.out.println("Contraseña:");
            password = scanner.nextLine();
            List<Usuario> usuario = persist.getUserByName(user,password);

            if (!usuario.isEmpty()) {
                while (true) {
                    System.out.println("(1) Dar de alta doctores.");
                    System.out.println("(2) Dar de alta pacientes.");
                    System.out.println("(3) Crear una cita con fecha y hora.");
                    System.out.println("(4) Relacionar una cita con un doctor y un paciente");
                    System.out.println("(5) Listar doctores");
                    System.out.println("(6) Listar pacientes");
                    System.out.println("(7) Buscar Pacientes");
                    System.out.println("(8) Buscar Doctores");
                    System.out.println("(0) Salir");
                    System.out.println("\nPor favor ingrese una opción: ");
                    // Fin de Menu
                    // Try Anidado
                    try {
                        // Asigna token Integer parseado
                        seleccion = scanner.nextInt();
                        String nombre,apellido,fechaNacimiento,direccion,especialidad;
                        switch (seleccion) {
                            case 0:
                                System.out.println("Saliendo..");
                                logger.info("Saliendo...");
                                return;
                            case 1:
                                // ingresar doctores
                                System.out.println("Por favor ingresa el nombre del doctor");
                                nombre= scanner.next();
                                System.out.println("Ingresa sus apelldios");
                                apellido=scanner.next();
                                System.out.println("Ingresa su fecha de nacimiento");
                                fechaNacimiento=scanner.next();
                                System.out.println("Ingresa su direccion completa");
                                direccion=scanner.next();
                                System.out.println("Ingresa la especialidad del doctor");
                                especialidad=scanner.next();
                                User doc = new Doctor(nombre,apellido,fechaNacimiento,direccion,especialidad);

                                doc.createDoctor(doc);
                                break;
                            case 2:


                                // ingresar pacientes

                                System.out.println("Por favor ingresa el nombre del paciente");
                                nombre= scanner.next();
                                System.out.println("Ingresa sus apelldios");
                                apellido=scanner.next();
                                System.out.println("Ingresa su fecha de nacimiento");
                                fechaNacimiento=scanner.next();
                                System.out.println("Ingresa su direccion completa");
                                direccion=scanner.next();
                                User paciente = new Patient(nombre,apellido,fechaNacimiento,direccion);

                                paciente.create(paciente,TABLA_PACIENTES);

                                break;
                            case 3:
                                String fecha,hora;

                                System.out.println("Ingrese la fecha de la cita");
                                fecha=scanner.next();
                                System.out.println("Ingresa la hora de la cita");
                                hora=scanner.next();

                                Cita newCita=new Cita(fecha,hora);
                                newCita.createCita();

                                break;
                            case 4:
                                int idCita,idPaciente,idDoctor;
                                System.out.println("Listado de citas disponibles");
                                Cita cit = new Cita();
                                cit.listarCitas();

                                System.out.println("Ingresa el id de la cita seleccionada");
                                idCita=scanner.nextInt();

                                System.out.println("Listado de Pacientes en espera");
                                User users= new User();
                                users.listarPacientes();

                                System.out.println("Ingresa el id del paciente seleccionado");
                                idPaciente=scanner.nextInt();

                                System.out.println("Listado de doctores");
                                users.listarDoctores();

                                System.out.println("Ingresa el Id del doctor seleccionado");
                                idDoctor=scanner.nextInt();
                                Cita nuevaCita= new Cita(idCita,idPaciente,idDoctor);
                                nuevaCita.generarCita();


                                break;
                            case 5:
                                //listado de todos los doctores
                                User docs= new User();
                                docs.listarDoctores();
                                break;
                            case 6:
                                User pacientes = new User();
                                pacientes.listarPacientes();
                                break;
                            case 7:
                                // buscar a pacientes

                                System.out.println("Ingresa el nombre del Paciente (Solo nombre)");
                                nombreBuscar=scanner.next();
                                User pacientess= new User();
                                pacientess.buscar(nombreBuscar,TABLA_PACIENTES);


                                break;
                            case 8:
                                // buscar a doctores

                                System.out.println("Ingresa el nombre del Doctro (Solo nombre)");
                                String nombreDoctor=scanner.next();
                                User doctor=new User();
                                doctor.buscarDoc(nombreDoctor);
                                break;
                            default:
                                System.err.println("Opción inválida.");
                                logger.error("Opción inválida: {}", seleccion);
                                break;
                        }

                    } catch (Exception ex) {
                        logger.error("{}: {}", ex.getClass(), ex.getMessage());
                        System.err.format("Ocurrió un error. Para más información consulta el log de la aplicación.");
                        scanner.next();
                    }
                }
            } else {
                System.out.println("No tiene autorización");
            }
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getClass(), ex.getMessage());
            System.err.format("Ocurrió un error. Para más información consulta el log de la aplicación.");
        } finally {
            persist.getConnection().close();
        }
    }
}
