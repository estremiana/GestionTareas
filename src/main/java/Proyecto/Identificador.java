package Proyecto;

import Proyecto.Menu.MenuHerramientas;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.*;

import java.util.List;
import java.util.Optional;

public class Identificador {
    public Identificador () { }

     GestionES entrada = new GestionES();

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

    public void herramienta (Proyecto proyecto) {
        MenuHerramientas accion = MenuHerramientas.getOpcion(entrada.indiceHerramienta());
        switch (accion) {
            case DAR_DE_ALTA_TRABAJADOR -> proyecto.darDeAltaTrabajador();
            case DAR_DE_ALTA_TAREA -> proyecto.darDeAltaTarea();
            case LISTAR_TAREAS -> proyecto.listarTareas();
            case LISTAR_PERSONAS_ASIGNADAS -> proyecto.listarPersonasAsignadasProyecto();
            case ANADIR_PERSONA_A_TAREA -> proyecto.anadirPersonaATareaOptional();
            case ELIMINAR_PERSONA_TAREA -> proyecto.eliminarPersonaDeTareaOptional();
            case MARCAR_TAREA_FINALIZADA -> proyecto.marcarTareaComoFinalizada();
            case SALIR -> {
                return;
            }
        }
        proyecto.elegirHerrramienta();

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
