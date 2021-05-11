package Controlador;

import Vista.Menu.MenuFacturacion;
import Vista.Menu.MenuHerramientas;
import Vista.Menu.MenuInicio;
import Modelo.Menu.MenuPrioridad;
import Modelo.Menu.MenuResultado;
import Modelo.Proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.*;

public class GestionES {
    Scanner teclado = new Scanner(System.in);

    public GestionES() { }


    //ENTRADA
    public String tituloTarea() {
        System.out.println("Introduce un título para la tarea:");
        return teclado.nextLine();
    }

    public String descripcionTarea() {
        System.out.println("Introduce una descripción para la tarea:");
        return teclado.nextLine();
    }

    public String nombreTrabajador() {
        System.out.println("Introduce el nombre del trabajador:");
        String nombre = teclado.nextLine();
        return nombre;
    }

    public String correoTrabajador() {
        System.out.println("Introduce el correo del trabajador:");
        String correo = teclado.nextLine();
        return correo;
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
        while (linea.length() != 0) {
            lista.add(linea);
            linea = teclado.nextLine();
        }
        return lista;
    }

    public int resultado() {
        System.out.println(MenuResultado.getMenu());
        System.out.println("Introduce una opción para el resultado de la tarea:");
        int intOpcion = Integer.parseInt(teclado.nextLine());;
        return intOpcion;
    }

    public int indiceInicio() {
        System.out.println(MenuInicio.getMenu());
        System.out.println("Elige una opción:");
        int indice = Integer.parseInt(teclado.nextLine());
        System.out.println(MenuInicio.getOpcion(indice).getDescripcion());
        return indice;
    }

    public int indiceHerramienta() {
        System.out.println(MenuHerramientas.getMenu());
        System.out.println("Elige una opción:");
        int indice = Integer.parseInt(teclado.nextLine());
        System.out.println(MenuHerramientas.getOpcion(indice).getDescripcion());
        return indice;
    }

    public int indiceFacturacion() {
        System.out.println(MenuFacturacion.getMenu());
        System.out.println("Elige una opción:");
        int indice = Integer.parseInt(teclado.nextLine());
        System.out.println(MenuFacturacion.getOpcion(indice).getDescripcion());
        return indice;
    }

    public MenuPrioridad prioridad() {
        System.out.println(MenuPrioridad.getMenu());
        System.out.println("Introduce una opción para la prioridad de la tarea:");
        int intOpcion = Integer.parseInt(teclado.nextLine());;
        return MenuPrioridad.values()[intOpcion];
    }



    public String nombreArchivo() {
        System.out.println("Introduce un nombre para el fichero:");
        return teclado.nextLine();
    }

    public float coste() {
        System.out.println("Introduce una coste para la tarea");
        return Float.parseFloat(teclado.nextLine());
    }


    //SALIDA
    public void listarPorPantalla(String lista) {
        System.out.println(lista);
    }

    public String nombreProyecto() {
        System.out.println("Introduce un nombre para el proyecto:");
        return teclado.nextLine();
    }

    public void cargaCorrecta(Proyecto proyecto) {
        System.out.println("El proyecto " + proyecto.getNombreProyecto() + " se ha cargado correctamente\n");
    }
}
