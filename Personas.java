
import java.util.Scanner;

class Persona {

    private String nombre;
    private int edad;
    private double sueldo;
    private String documento;
    private char sexo;

    public Persona(String nombre, int edad, double sueldo, String documento, char sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sueldo = sueldo;
        this.documento = documento;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getDocumento() {
        return documento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void mostrar() {
        System.out.println("---------------------------");
        System.out.println("Nombre    : " + nombre);
        System.out.println("Documento : " + documento);
        System.out.println("Edad      : " + edad);
        System.out.println("Sueldo    : $" + sueldo);
        System.out.println("Sexo      : " + sexo);
        System.out.println("---------------------------");
    }
}

public class Personas {

    static Persona[] personas = new Persona[100];
    static int totalPersonas = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n=============================");
            System.out.println("   GESTIÓN DE PERSONAS");
            System.out.println("=============================");
            System.out.println("1. Registrar persona");
            System.out.println("2. Consultar persona");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Listar personas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    actualizar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    listar();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    static void registrar() {
        if (totalPersonas >= 100) {
            System.out.println("El arreglo está lleno.");
            return;
        }

        System.out.println("\n--- Registrar nueva persona ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Documento de identidad: ");
        String documento = sc.nextLine();

        if (buscarIndice(documento) != -1) {
            System.out.println("Ya existe una persona con ese documento.");
            return;
        }

        System.out.print("Edad: ");
        int edad = sc.nextInt();

        System.out.print("Sueldo: ");
        double sueldo = sc.nextDouble();
        sc.nextLine();

        System.out.print("Sexo (M/F): ");
        char sexo = sc.nextLine().toUpperCase().charAt(0);

        personas[totalPersonas] = new Persona(nombre, edad, sueldo, documento, sexo);
        totalPersonas++;

        System.out.println("Persona registrada correctamente.");
    }

    static void consultar() {
        System.out.print("\nIngrese el documento a buscar: ");
        String documento = sc.nextLine();

        int indice = buscarIndice(documento);

        if (indice == -1) {
            System.out.println("No se encontró ninguna persona con ese documento.");
        } else {
            System.out.println("\n--- Datos de la persona ---");
            personas[indice].mostrar();
        }
    }

    static void actualizar() {
        System.out.print("\nIngrese el documento de la persona a actualizar: ");
        String documento = sc.nextLine();

        int indice = buscarIndice(documento);

        if (indice == -1) {
            System.out.println("No se encontró ninguna persona con ese documento.");
            return;
        }

        System.out.println("Persona encontrada. Ingrese los nuevos datos:");

        System.out.print("Nuevo nombre (" + personas[indice].getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) {
            personas[indice].setNombre(nombre);
        }

        System.out.print("Nueva edad (" + personas[indice].getEdad() + ") — 0 para no cambiar: ");
        int edad = sc.nextInt();
        if (edad != 0) {
            personas[indice].setEdad(edad);
        }

        System.out.print("Nuevo sueldo (" + personas[indice].getSueldo() + ") — 0 para no cambiar: ");
        double sueldo = sc.nextDouble();
        if (sueldo != 0) {
            personas[indice].setSueldo(sueldo);
        }
        sc.nextLine();

        System.out.print("Nuevo sexo (" + personas[indice].getSexo() + ") — Enter para no cambiar: ");
        String sexoStr = sc.nextLine();
        if (!sexoStr.isEmpty()) {
            personas[indice].setSexo(sexoStr.toUpperCase().charAt(0));
        }

        System.out.println("Persona actualizada correctamente.");
    }

    static void eliminar() {
        System.out.print("\nIngrese el documento de la persona a eliminar: ");
        String documento = sc.nextLine();

        int indice = buscarIndice(documento);

        if (indice == -1) {
            System.out.println("No se encontró ninguna persona con ese documento.");
            return;
        }

        for (int i = indice; i < totalPersonas - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[totalPersonas - 1] = null;
        totalPersonas--;

        System.out.println("Persona eliminada correctamente.");
    }

    static void listar() {
        if (totalPersonas == 0) {
            System.out.println("\nNo hay personas registradas.");
            return;
        }

        System.out.println("\n--- Lista de personas (" + totalPersonas + ") ---");
        for (int i = 0; i < totalPersonas; i++) {
            System.out.println("\nPersona #" + (i + 1));
            personas[i].mostrar();
        }
    }

    static int buscarIndice(String documento) {
        for (int i = 0; i < totalPersonas; i++) {
            if (personas[i].getDocumento().equals(documento)) {
                return i;
            }
        }
        return -1;
    }
}
