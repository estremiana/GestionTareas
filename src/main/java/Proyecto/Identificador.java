package Proyecto;

import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.*;

import java.util.List;
import java.util.Optional;

public class Identificador {
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


    //OPTIONALS

    public Optional<Tarea> tarea (String tituloTarea, List<Tarea> tareas) {
        for (Tarea tarea : tareas) {
            if (tarea.titulo.equals(tituloTarea))
                return Optional.of(tarea);
        }
        return Optional.empty();
    }

    public Optional<Persona> personaOpcional(String correo, List<Persona> personas) {
        for (Persona persona : personas) {
            if (correo.equals(persona.correoElectronico)) {
                return Optional.of(persona);
            }
        }
        return Optional.empty();
    }


}
