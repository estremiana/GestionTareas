package Proyecto;

import Proyecto.Menu.MenuHerramientas;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.Resultado;

import java.util.*;
import java.util.List;

public class Proyecto {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    Identificador identificar = new Identificador();

    GestionES entrada = new GestionES();

    public Proyecto () {
        this.nombreProyecto = entrada.nombreProyecto();
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public Proyecto (String nombre) {
        this.nombreProyecto = nombre;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public void elegirHerrramienta () {
        MenuHerramientas opcion;
        do {
            opcion = MenuHerramientas.getOpcion(entrada.indiceHerramienta());
            opcion.accion(this);
        } while (opcion != MenuHerramientas.SALIR);

    }

    //public void darDeAltaTrabajador () {
    //    this.personas.add(new Persona(entrada.nombreTrabajador(), entrada.correoTrabajador()));
    //}

    //public void darDeAltaTarea () {
    //    String titulo = entrada.tituloTarea();
    //    String descripcion = entrada.descripcionTarea();
    //    Persona responsable = identificar.personaOpcional(entrada.correoPersonaResponsableTarea(), personas).orElse(new Persona());
    //    List<Persona> personasAsignadas = pedirPersonasAsignadas();
    //    MenuPrioridad prioridad = MenuPrioridad.getOpcion(entrada.prioridad());
    //    Resultado resultado = identificar.resultado(MenuResultado.getOpcion(entrada.resultado()));
    //    List<String> etiquetas = entrada.etiquetasTarea();
    //    Tarea nuevaTarea = new Tarea(titulo, descripcion, personasAsignadas, responsable, prioridad, resultado, etiquetas);
    //    tareas.add(nuevaTarea);
    //    responsable.asignarTarea(nuevaTarea);
    //}

    private List<Persona> pedirPersonasAsignadas() {
        List<Persona> personasAsignadas = new ArrayList<>();
        for (String correo : entrada.correosPersonasAsignadasTarea())
            identificar.personaOpcional(correo, personas).ifPresent(personasAsignadas::add);    //Añade solo cuando la persona no es empty
        return personasAsignadas;
    }

    //public String listarTareas () {
    //    StringBuilder lista = new StringBuilder();
    //    lista.append("Las tareas del proyecto ").append(nombreProyecto).append(" son: \n");
    //    for (Tarea tarea : tareas) {
    //        lista.append(tarea.toString());
    //    }
    //    return lista.toString();
    //}

    //public String listarPersonasAsignadasProyecto() {
    //    StringBuilder lista = new StringBuilder("Las personas asignadas al proyecto " + nombreProyecto + " son:\n");
    //    for (Persona persona : personas) {
    //        lista.append(persona.toString()).append("\n");
    //    }
    //    return lista.toString();
    //}

    //public void anadirPersonaATareaOptional() {
    //    Optional<Tarea> tarea = identificar.tarea(entrada.tituloTarea(), tareas);
    //    Optional<Persona> persona = identificar.personaOpcional(entrada.correoTrabajador(), personas);
    //    tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::añadirPersona));
    //}

    //public void eliminarPersonaDeTareaOptional () {
    //    Optional<Tarea> tarea = identificar.tarea(entrada.tituloTarea(), tareas);
    //    Optional<Persona> persona = identificar.personaOpcional(entrada.correoTrabajador(), personas);
    //    tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::eliminarPersona));
    //}

    //public void marcarTareaComoFinalizada() {
    //    identificar.tarea(entrada.tituloTarea(), tareas).ifPresent(Tarea::finalizar);
    //}


    public String getNombre() {
        return nombreProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }
}
