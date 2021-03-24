import Menú.MenuPrioridad;
import Menú.MenuResultado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestiónES {
    Scanner teclado = new Scanner(System.in);
    Identificador identificar = new Identificador();

    public GestiónES () { }

    public String tituloTarea() {
        System.out.println("Introduce un título para la tarea:");
        return teclado.nextLine();
    }

    public String descripcionTarea() {
        System.out.println("Introduce una descripción para la tarea:");
        return teclado.nextLine();
    }

    public String correoPersonaResponsableTarea() {
        System.out.println("Introduce el correo del responsable de la tarea:");
        return teclado.nextLine();
    }

    public List<String> correosPersonasAsignadasTarea() {
        System.out.println("Introduce el correo de las personas asignadas a la tarea (presiona enter para finalizar):");
        List<String> lista = new ArrayList<>();
        String linea = teclado.nextLine();
        while (linea.length() != 0) {
            lista.add(linea);
            linea = teclado.nextLine();
        }
        return lista;
    }

    public List<String> etiquetasTarea() {
        System.out.println("Introduce las etiquetas de la tarea (presiona enter para finalizar):");
        List<String> lista = new ArrayList<>();
        String linea = teclado.nextLine();
        while (linea.length() > 0) {
            lista.add(linea);
            linea = teclado.nextLine();
        }
        return lista;
    }

    public int resultado() {
        MenuResultado opcion;
        System.out.println(MenuResultado.getMenu());
        System.out.println("Introduce una opción para el resultado de la tarea:");
        int intOpcion = teclado.nextInt();
        return intOpcion;
    }

    public int prioridad() {
        MenuPrioridad opcion;
        System.out.println(MenuPrioridad.getMenu());
        System.out.println("Introduce una opción para la prioridad de la tarea:");
        int intOpcion = teclado.nextInt();
        opcion = MenuPrioridad.getOpcion(intOpcion);
        return identificar.prioridad(opcion);
    }
}
