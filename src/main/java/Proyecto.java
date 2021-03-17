import java.awt.*;
import java.util.*;
import java.util.List;

public class Proyecto {
    Scanner teclado = new Scanner(System.in);
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    public Proyecto (String nombre) {
        this.nombreProyecto = nombre;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public void darDeAltaTrabajador (String nombre, String correo) {
        this.personas.add(new Persona(nombre, correo));
    }

    public void darDeAltaTarea () {
        String titulo = entradaTituloTarea();
        String descripcion = entradaDescripcionTarea();
        Persona responsable = identificarPersonaOpcional(entradaCorreoPersonaResponsableTarea()).orElse(new Persona());
        List<Persona> personasAsignadas = new ArrayList<>();
        for (String correo : entradaCorreosPersonasAsignadasTarea())
            personasAsignadas.add(identificarPersonaOpcional(correo).orElse(new Persona()));
        int numeroPrioridad = entradaPrioridad();
        Resultado resultado = entradaResultado();
        List<String> etiquetas = entradaEtiquetasTarea();
        Tarea nuevaTarea = new Tarea(titulo, descripcion, personasAsignadas, responsable, numeroPrioridad, resultado, etiquetas);
        tareas.add(nuevaTarea);
        responsable.asignarTarea(nuevaTarea);
    }



    public String listarTareas () {
        StringBuilder lista = new StringBuilder();
        lista.append("Las tareas del proyecto ").append(nombreProyecto).append(" son: \n");
        for (Tarea tarea : tareas) {
            lista.append(tarea.toString());
        }
        return lista.toString();
    }

    public String listarPersonasAsignadasProyecto() {
        StringBuilder lista = new StringBuilder("Las personas asignadas al proyecto " + nombreProyecto + " son:\n");
        for (Persona persona : personas) {
            lista.append(persona.toString()).append("\n");
        }
        return lista.toString();
    }

    public void añadirPersonaATarea (String correo, String tituloTarea) {
        Optional<Tarea> tarea = identificarTarea(tituloTarea);
        Optional<Persona> persona = identificarPersonaOpcional(correo);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::añadirPersona));
    }

    public void eliminarPersonaDeTareaOptional (String correo, String tituloTarea) {
        Optional<Tarea> tarea = identificarTarea(tituloTarea);
        Optional<Persona> persona = identificarPersonaOpcional(correo);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::eliminarPersona));

    }

    public void marcarTareaComoFinalizada(String tituloTarea) {
        identificarTarea(tituloTarea).ifPresent(Tarea::finalizar);
    }

    private Resultado identificarResultado(MenuResultado tipo) {
        return switch (tipo) {
            case BIBLIOTECA -> crearBiblioteca();
            case DOCUMENTACION -> crearDocumentación();
            case PAGINAWEB -> crearPaginaWeb();
            case PROGRAMA -> crearPrograma();
        };
    }

    private Resultado crearPrograma() {
        return new Programa();
    }

    private Resultado crearPaginaWeb() {
        return new PaginaWeb();
    }

    private Resultado crearDocumentación() {
        return new Documentación();
    }

    private Resultado crearBiblioteca() {
        return new Biblioteca();
    }

    private int identificarPrioridad(MenuPrioridad prioridad) {
        return switch (prioridad) {
            case ALTA -> 2;
            case MEDIA -> 1;
            case BAJA -> 0;
        };
    }

    private Optional<Persona> identificarPersonaOpcional(String correo) {
        for (Persona persona : personas) {
            if (correo.equals(persona.correoElectronico)) {
                return Optional.of(persona);
            }
        }
        return Optional.empty();
    }

    private Optional<Tarea> identificarTarea (String tituloTarea) {
        for (Tarea tarea : tareas) {
            if (tarea.titulo.equals(tituloTarea))
                return Optional.of(tarea);
        }
        return Optional.empty();
    }

    private String entradaTituloTarea() {
        System.out.println("Introduce un título para la tarea:");
        return teclado.nextLine();
    }

    private String entradaDescripcionTarea() {
        System.out.println("Introduce una descripción para la tarea:");
        return teclado.nextLine();
    }

    private String entradaCorreoPersonaResponsableTarea() {
        System.out.println("Introduce el correo del responsable de la tarea:");
        return teclado.nextLine();
    }

    private List<String> entradaCorreosPersonasAsignadasTarea() {
        System.out.println("Introduce el correo de las personas asignadas a la tarea (presiona enter para finalizar):");
        List<String> lista = new ArrayList<>();
        String linea = teclado.nextLine();
        while (linea.length() != 0) {
            lista.add(linea);
            linea = teclado.nextLine();
        }
        return lista;
    }

    private List<String> entradaEtiquetasTarea() {
        System.out.println("Introduce las etiquetas de la tarea (presiona enter para finalizar):");
        List<String> lista = new ArrayList<>();
        String linea = teclado.nextLine();
        while (linea.length() != 0) {
            lista.add(linea);
            linea = teclado.nextLine();
        }
        return lista;
    }

    private Resultado entradaResultado() {
        MenuResultado opcion;
        System.out.println(MenuResultado.getMenu());
        System.out.println("Introduce una opción para el resultado de la tarea:");
        int intOpcion = teclado.nextInt();
        opcion = MenuResultado.getOpcion(intOpcion);
        return identificarResultado(opcion);
    }

    private int entradaPrioridad() {
        MenuPrioridad opcion;
        System.out.println(MenuPrioridad.getMenu());
        System.out.println("Introduce una opción para la prioridad de la tarea:");
        int intOpcion = teclado.nextInt();
        opcion = MenuPrioridad.getOpcion(intOpcion);
        return identificarPrioridad(opcion);
    }


}
