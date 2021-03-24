import Menú.MenuPrioridad;
import Menú.MenuResultado;
import Resultado.*;

import java.util.List;
import java.util.Optional;

public class Identificador {
    public Identificador () { }

    public int prioridad(MenuPrioridad prioridad) {
        return switch (prioridad) {
            case ALTA -> 2;
            case MEDIA -> 1;
            case BAJA -> 0;
        };
    }

    public Optional<Tarea> tarea (String tituloTarea, List<Tarea> tareas) {
        for (Tarea tarea : tareas) {
            if (tarea.titulo.equals(tituloTarea))
                return Optional.of(tarea);
        }
        return Optional.empty();
    }

    public Resultado resultado(MenuResultado tipo) {
        return switch (tipo) {
            case BIBLIOTECA -> new Biblioteca();
            case DOCUMENTACION -> new Documentacion();
            case PAGINAWEB -> new PaginaWeb();
            case PROGRAMA -> new Programa();
        };
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
