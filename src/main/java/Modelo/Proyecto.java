package Modelo;

import Modelo.Excepciones.PersonaNoPerteneceException;
import Modelo.Excepciones.PersonaYaPerteneceException;
import Modelo.Menu.MenuPrioridad;
import Modelo.Menu.MenuResultado;
import Modelo.Resultado.Resultado;
import Modelo.Interfaces.tieneLista;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Proyecto implements tieneLista<Persona>, Serializable {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    Identificador identificar = new Identificador();

    public Proyecto (String titulo) {
        this.nombreProyecto = titulo;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public Proyecto() {}

    public void darDeAltaTrabajador (String nombre, String correo) {
        Persona persona = new Persona(nombre, correo);
        try {
            anadirTrabajador(persona);
        } catch (PersonaYaPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void darDeAltaTarea (String titulo, String descripcion, int intPrioridad, MenuResultado tipo, List<String> etiquetas) {
        MenuPrioridad prioridad = MenuPrioridad.getOpcion(intPrioridad);
        Resultado resultado = identificar.resultado(tipo);
        Tarea nuevaTarea = new Tarea(titulo, descripcion, prioridad, resultado, etiquetas);
        tareas.add(nuevaTarea);
    }

    public void asignarResponsableATarea (String tituloTarea, String correoResponsable) {
        Tarea tarea;
        Persona persona;
        try {
                tarea = identificar.tarea(tituloTarea, tareas);
                persona = identificar.persona(correoResponsable, personas);
                tarea.anadirResponsable(persona);
        } catch (IllegalArgumentException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public String listarTareas () {
        StringBuilder lista = new StringBuilder();
        lista.append("\n");
        if (tareas.isEmpty()) {
            lista.append("No hay tareas asignadas al proyecto ").append(nombreProyecto).append("\n");
        } else {
            lista.append("Las tareas del proyecto ").append(nombreProyecto).append(" son: \n");
            for (Tarea tarea : tareas) {
                lista.append(tarea.toString()).append("\n");
            }
        }
        return lista.toString();
    }

    public String listarPersonasAsignadasProyecto() {
        StringBuilder lista = new StringBuilder();
        if (personas.isEmpty()) {
            lista.append("No hay personas asignadas al proyecto ").append(nombreProyecto).append("\n");
        } else {
            lista.append("Las personas asignadas al proyecto ").append(nombreProyecto).append(" son:\n");
            for (Persona persona : personas) {
                lista.append(persona.toString()).append("\n");
            }
        }
        return lista.toString();
    }

    public void anadirPersonaATarea(String titulo, String correo) {
        try {
            Tarea tarea = identificar.tarea(titulo, tareas);
            Persona persona = identificar.persona(correo, personas);
            tarea.anadirPersona(persona);
        } catch (IllegalArgumentException | PersonaYaPerteneceException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersonaDeTarea(String titulo, String correo) {
        try {
            Tarea tarea = identificar.tarea(titulo, tareas);
            Persona persona = identificar.persona(correo, personas);
            tarea.eliminarPersona(persona);
        } catch (IllegalArgumentException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void marcarTareaComoFinalizada(String titulo) {
        try {
            identificar.tarea(titulo, tareas).finalizar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public List<Persona> listaPersonasNoResponsables(List<Persona> personas) {
        return UtilidadesParaListas.elementosConListaVacia(personas);
    }

    public List<Tarea> listaTareasSinPersonas() {
        return UtilidadesParaListas.elementosConListaVacia(tareas);
    }


    public void anadirTrabajador(Persona persona) throws PersonaYaPerteneceException {
        if (UtilidadesParaListas.sePuedeInsertar(persona, this))
            this.personas.add(persona);
        else throw new PersonaYaPerteneceException(persona);
    }

    public void anadirCosteATarea(String tituloTarea, float coste) {
        try {
            identificar.tarea(tituloTarea, tareas).setCoste(coste);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //GETTERS

    public List<Persona> getTrabajadores() {
        return personas;
    }
    public List<Tarea> getTareas() {
        return tareas;
    }
    public String getNombreProyecto() {return nombreProyecto;}


    @Override
    public List<Persona> getLista() {
        return personas;
    }
}
