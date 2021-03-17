import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public void darDeAltaTarea (String titulo, String descripcion, String correoPersonaResponsable, List<String> correosPersonasAsignadas, String cadenaPrioridad, String tipo, List<String> etiquetas) {
        Persona responsable = identificarPersona(correoPersonaResponsable);
        List<Persona> personasAsignadas = new ArrayList<>();
        for (String correo : correosPersonasAsignadas)
            personasAsignadas.add(identificarPersona(correo));
        int numeroPrioridad = identificarPrioridad(cadenaPrioridad);
        Resultado resultado = identificarResultado(tipo);
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
        Tarea tarea = identificarTarea(tituloTarea).get();
        tarea.añadirPersona(identificarPersona(correo));
    }

    public void eliminarPersonaDeTareaOptional (String correo, String tituloTarea) {
        Optional<Tarea> tarea = identificarTarea(tituloTarea);
        tarea.eliminarPersona(identificarPersona(correo));
    }

    public void marcarTareaComoFinalizada(String tituloTarea) {
        identificarTarea(tituloTarea).finalizar();
    }

    private Resultado identificarResultado(String tipo) {
        return switch (tipo) {
            case "Biblioteca" -> new Biblioteca();
            case "Documentación" -> new Documentación();
            case "Página web" -> new PaginaWeb();
            case "Programa" -> new Programa();
            default -> null;
        };
    }

    private int identificarPrioridad(String prioridad) {
        return switch (prioridad) {
            case "Alta" -> 2;
            case "Media" -> 1;
            case "Baja" -> 0;
            default ->1;
        };
    }

    private Optional<Persona> identificarPersona(String correo) {
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

    public String getTituloTarea() {
        String titulo;
        System.out.println("Introduce un título para la tarea");
        titulo = teclado.nextLine();
        return titulo;
    }


}
