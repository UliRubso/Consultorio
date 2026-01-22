import com.sun.source.tree.IfTree;
import jdk.dynalink.beans.StaticClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  static List<Medico> listaMedicos = new ArrayList<>();

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    Usuario usuario = new Usuario();
    int accion = 0;

    System.out.println("Introduce el Usuario");
    usuario.usuario = entrada.nextLine();

    System.out.println("Introduce la Contraseña");
    usuario.password = entrada.nextLine();

    if (usuario.esDatosValidos("ULISES", "123")) {
      System.out.println("Bienvenido");

      while (accion != 7) {
        menu();
        accion = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer
        aplicarAccion(accion);
      }
    } else {
      System.out.println("Error de credenciales");
    }
  }

  static void menu() {
    System.out.println("1.-Dar de alta medico");
    System.out.println("2.-Dar de paciente");
    System.out.println("3.-Crear cita");
    System.out.println("4.-Mostrar medicos");
    System.out.println("5.-Mostrar pacientes");
    System.out.println("6.-Mostrar citas");
    System.out.println("7.-Salir");
  }

  static void aplicarAccion(int accion) {
    switch (accion) {
      case 1:
        darAltaMedico();
        break;
      case 2:
        darAltaPaciente();
        break;
      case 3:
        crearCita();
        break;
      case 4:
        mostrarMedicos();
        break;
      case 5:
        mostrarPacientes();
        break;
      case 6:
        mostrarCitas();
        break;
    }
  }

  static void darAltaMedico() {
    Scanner entrada = new Scanner(System.in);

    System.out.println("Dar de alta medico");

    Medico medico = new Medico();

    System.out.println("Nombre:");
    medico.nombre = entrada.nextLine();

    System.out.println("Especialidad:");
    medico.especialidad = entrada.nextLine();

    listaMedicos.add(medico);

    System.out.println("Medico registrado correctamente");
  }

  static void darAltaPaciente() {
    System.out.println("Dar de alta paciente");
  }

  static void crearCita() {
    System.out.println("Crear cita");
  }

  static void mostrarMedicos() {
    System.out.println("Mostrar medicos");

    if (listaMedicos.isEmpty()) {
      System.out.println("No hay medicos registrados");
      return;
    }

    for (Medico medico : listaMedicos) {
      System.out.println("Nombre: " + medico.nombre);
      System.out.println("Especialidad: " + medico.especialidad);
      System.out.println("---------------------");
    }
  }

  static void mostrarPacientes() {
    System.out.println("Mostrar pacientes");
  }

  static void mostrarCitas() {
    System.out.println("Mostrar citas");
  }
}
