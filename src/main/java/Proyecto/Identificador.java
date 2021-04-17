package Proyecto;

import Proyecto.Excepciones.PersonaNoPerteneceException;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.*;

import java.io.Serializable;
import java.util.List;

public class Identificador implements Serializable {
    public Identificador () { }

    //ENUMS

    public int prioridad(MenuPrioridad prioridad) {
        return switch (prioridad) {
            case ALTA -> 0;
            case MEDIA -> 1;
            case BAJA -> 2;
        };
    }

    public Resultado resultado(MenuResultado tipo) {
        return switch (tipo) {
            case BIBLIOTECA -> new Biblioteca();
            case DOCUMENTACION -> new Documentacion();
            case PAGINAWEB -> new PaginaWeb();
            case PROGRAMA -> new Programa();
        };
    }


    //PROYECTO

    public Tarea tarea (String tituloTarea, List<Tarea> tareas)
    throws IllegalArgumentException{
        for (Tarea tarea : tareas) {
            if (tarea.titulo.equals(tituloTarea))
                return tarea;
        }
        throw new IllegalArgumentException("La tarea " + tituloTarea + " no se ha encontrado");
    }

    public Persona persona(String correo, List<Persona> personas)
            throws PersonaNoPerteneceException {
        for (Persona persona : personas) {
            if (correo.equals(persona.correoElectronico)) {
                return persona;
            }
        }
        throw new PersonaNoPerteneceException(correo);
    }


}
