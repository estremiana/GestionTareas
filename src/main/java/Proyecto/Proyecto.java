package Proyecto;

import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.Resultado;
import Proyecto.UtilidadesParaListas;

import java.util.*;
import java.util.List;

public class Proyecto {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    Identificador identificar = new Identificador();

    public Proyecto (String titulo) {
        this.nombreProyecto = titulo;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }



    public void darDeAltaTrabajador (String nombre, String correo) {
        this.personas.add(new Persona(nombre, correo));
    }

    public void darDeAltaTarea (String titulo, String descripcion, String correoPersonaResponsable, int intPrioridad, MenuResultado tipo, List<String> etiquetas) {
        Optional<Persona> responsable = identificar.personaOpcional(correoPersonaResponsable, personas);
        MenuPrioridad prioridad = MenuPrioridad.getOpcion(intPrioridad);
        Resultado resultado = identificar.resultado(tipo);
        Tarea nuevaTarea = new Tarea(titulo, descripcion, responsable, prioridad, resultado, etiquetas);
        responsable.ifPresent(x -> nuevaTarea.getListaPersonasAsignadas().add(x));
        tareas.add(nuevaTarea);
        responsable.ifPresent(x -> x.asignarTarea(nuevaTarea));
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

    public void anadirPersonaATareaOptional(String titulo, String correo) {
        Optional<Tarea> tarea = identificar.tarea(titulo, tareas);
        Optional<Persona> persona = identificar.personaOpcional(correo, personas);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::añadirPersona));
    }

    public void eliminarPersonaDeTareaOptional (String titulo, String correo) {
        Optional<Tarea> tarea = identificar.tarea(titulo, tareas);
        Optional<Persona> persona = identificar.personaOpcional(correo, personas);
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::eliminarPersona));
    }

    public void marcarTareaComoFinalizada(String titulo) {
        identificar.tarea(titulo, tareas).ifPresent(Tarea::finalizar);
    }

    public List<Persona> listaPersonasNoResponsables(List<Persona> personas) {
        return UtilidadesParaListas.elementosConListaVacia(personas);
    }

    public List<Tarea> listaTareasSinPersonas() {
        return UtilidadesParaListas.elementosConListaVacia(tareas);
    }

    //GETTERS

    public List<Persona> getTrabajadores() {
        return personas;
    }
    public List<Tarea> getTareas() {
        return tareas;
    }



}
