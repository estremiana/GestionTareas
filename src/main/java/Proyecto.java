import Menú.MenuResultado;
import Resultado.Resultado;

import java.util.*;
import java.util.List;

public class Proyecto {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    Identificador identificar = new Identificador();

    GestiónES entrada = new GestiónES();

    public Proyecto (String nombre) {
        this.nombreProyecto = nombre;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public void darDeAltaTrabajador (String nombre, String correo) {
        this.personas.add(new Persona(nombre, correo));
    }

    public void darDeAltaTarea () {
        String titulo = entrada.tituloTarea();
        String descripcion = entrada.descripcionTarea();
        Persona responsable = identificar.personaOpcional(entrada.correoPersonaResponsableTarea(), personas).orElse(new Persona());
        List<Persona> personasAsignadas = new ArrayList<>();
        for (String correo : entrada.correosPersonasAsignadasTarea())
            personasAsignadas.add(identificar.personaOpcional(correo, personas).orElse(new Persona()));
        int numeroPrioridad = entrada.prioridad();
        Resultado resultado = identificar.resultado(MenuResultado.getOpcion(entrada.resultado()));
        List<String> etiquetas = entrada.etiquetasTarea();
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
        Optional<Tarea> tarea = identificar.tarea(tituloTarea, tareas);
        Optional<Persona> persona = identificar.personaOpcional(correo, personas);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::añadirPersona));
    }

    public void eliminarPersonaDeTareaOptional (String correo, String tituloTarea) {
        Optional<Tarea> tarea = identificar.tarea(tituloTarea, tareas);
        Optional<Persona> persona = identificar.personaOpcional(correo, personas);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::eliminarPersona));

    }

    public void marcarTareaComoFinalizada(String tituloTarea) {
        identificar.tarea(tituloTarea, tareas).ifPresent(Tarea::finalizar);
    }




}
