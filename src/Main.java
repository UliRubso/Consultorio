static List<Medico> listaMedicos = new ArrayList<>();
static List<Paciente> listaPacientes = new ArrayList<>();
static List<Cita> listaCitas = new ArrayList<>();
static Scanner entrada = new Scanner(System.in);

void main() {
    Usuario usuario = new Usuario();
    int accion = 0;

    IO.println("Introduce el Usuario");
    usuario.usuario = entrada.nextLine();

    IO.println("Introduce la Contraseña");
    usuario.password = entrada.nextLine();

    if (usuario.esDatosValidos("ULISES", "123")) {
        IO.println("Bienvenido");

        while (accion != 7) {
            menu();
            accion = entrada.nextInt();
            entrada.nextLine(); // limpiar buffer
            aplicarAccion(accion);
        }
        guardarMedicos();
        guardarPacientes();
        guardarCitas();

    } else {
        IO.println("Error de credenciales");
    }
}

static void menu() {
    IO.println("1.-Dar de alta medico");
    IO.println("2.-Dar de paciente");
    IO.println("3.-Crear cita");
    IO.println("4.-Mostrar medicos");
    IO.println("5.-Mostrar pacientes");
    IO.println("6.-Mostrar citas");
    IO.println("7.-Salir");
}

static void aplicarAccion(int opcion) {
    switch (opcion) {
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
    Medico m = new Medico();
    IO.println("Dar de alta medico");
    m.nombre = entrada.nextLine();

    IO.println("Especialidad:");
    m.especialidad = entrada.nextLine();

    listaMedicos.add(m);
    IO.println("Medico registrado correctamente");
}

static void mostrarMedicos() {
    if (listaMedicos.isEmpty()) {
        IO.println("no hay medicos");
        return;
    }
    for (int i = 0; i < listaMedicos.size(); i++) {
        Medico m = listaMedicos.get(i);
        IO.println(i + "-" + m.nombre + "(" + m.especialidad + ")");
    }
}

static void guardarMedicos() {
    try (PrintWriter pw = new PrintWriter(new PrintWriter("medicos.txt"))) {
        for (Medico m : listaMedicos) {
            pw.println(m.nombre + ";" + m.especialidad);
        }
    } catch (IOException e) {
        IO.println("Falla al guardar");
    }
}

static void darAltaPaciente() {
    Paciente p = new Paciente();
    IO.println("Nombre del paciente");
    p.nombre = entrada.nextLine();
    IO.println("Edad");
    p.edad = entrada.nextLine();

    listaPacientes.add(p);
    IO.println("paciente registrado");
}

static void mostrarPacientes() {
    if (listaPacientes.isEmpty()) {
        IO.println("No hay pacientes");
        return;
    }
    for (int i = 0; i < listaPacientes.size(); i++) {
        Paciente p = listaPacientes.get(i);
        IO.println(i + "-" + p.nombre + "(Edad " + p.edad + ")");
    }
}

static void guardarPacientes() {
    try (PrintWriter pw = new PrintWriter(new PrintWriter("pacientes.txt"))) {
        for (Paciente p : listaPacientes) {
            pw.println(p.nombre + ";" + p.edad);
        }
    } catch (IOException e) {
        IO.println("Falla al guardar");
    }
}

static void crearCita() {
    if (listaMedicos.isEmpty() || listaPacientes.isEmpty()) {
        IO.println("no hay medicos ni pacientes");
        return;
    }
    mostrarMedicos();
    IO.println("seleccione medico (indice):");
    int m = entrada.nextInt();
    entrada.nextLine();

    mostrarPacientes();
    IO.println("seleccione paciente (indice):");
    int p = entrada.nextInt();
    entrada.nextLine();

    IO.println("Fecha");
    String fecha = entrada.nextLine();

    Cita c = new Cita();
    c.medico = listaMedicos.get(m);
    c.paciente = listaPacientes.get(p);
    c.fecha = fecha;

    listaCitas.add(c);
    IO.println("cita creada");
}

static void mostrarCitas() {
    if (listaCitas.isEmpty()) {
        IO.println("no hay citas");
        return;
    }
    for (Cita c : listaCitas) {
        IO.println("paciente:" + c.paciente.nombre + "medico:" + c.medico.nombre + "fecha:" + c.fecha);
    }
}

static void guardarCitas() {
    try (PrintWriter pw = new PrintWriter(new FileWriter("citas.txt"))) {
        for (Cita c : listaCitas) {
            pw.println(c.paciente.nombre + ";" + c.medico.nombre + ";" + c.fecha);
        }
    } catch (IOException e) {
        IO.println("Error");
    }
}