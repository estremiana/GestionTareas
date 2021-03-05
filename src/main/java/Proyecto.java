import java.util.ArrayList;
import java.util.List;

public class Proyecto {
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
        lista.append("Las tares del proyecto ").append(nombreProyecto).append(" son: \n");
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
        Tarea tarea = identificarTarea(tituloTarea);
        tarea.añadirPersona(identificarPersona(correo));
    }

    public void eliminarPersonaDeTarea (String correo, String tituloTarea) {
        Tarea tarea = identificarTarea(tituloTarea);
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

    private Persona identificarPersona(String correo) {
        for (Persona persona : personas) {
            if (correo.equals(persona.correoElectronico)) {
                return persona;
            }
        }
        return null;
    }

    private Tarea identificarTarea (String tituloTarea) {
        for (Tarea tarea : tareas) {
            if (tarea.titulo.equals(tituloTarea))
                return tarea;
        }
        return null;
    }
}
