package Main;

import Proyecto.Identificador;
import Proyecto.Menu.MenuHerramientas;
import Proyecto.Menu.MenuResultado;
import Proyecto.Proyecto;

public class Interfaz {
    public Interfaz() {}

    GestionES entrada = new GestionES();
    GestionES salida = new GestionES();
    Identificador identificador = new Identificador();

    public void herramienta (Proyecto proyecto) {
        MenuHerramientas accion = MenuHerramientas.getOpcion(entrada.indiceHerramienta());
        switch (accion) {
            case DAR_DE_ALTA_TRABAJADOR -> {
                String nombre = entrada.nombreTrabajador();
                String correo = entrada.correoTrabajador();
                proyecto.darDeAltaTrabajador(nombre, correo);
            }
            case DAR_DE_ALTA_TAREA -> proyecto.darDeAltaTarea(entrada.tituloTarea(), entrada.descripcionTarea(), entrada.correoPersonaResponsableTarea(), identificador.prioridad(entrada.prioridad()), MenuResultado.getOpcion(entrada.resultado()), entrada.etiquetasTarea());
            case LISTAR_TAREAS -> salida.listarPorPantalla(proyecto.listarTareas());
            case LISTAR_PERSONAS_ASIGNADAS -> salida.listarPorPantalla(proyecto.listarPersonasAsignadasProyecto());
            case ANADIR_PERSONA_A_TAREA -> proyecto.anadirPersonaATareaOptional(entrada.tituloTarea(), entrada.correoTrabajador());
            case ELIMINAR_PERSONA_TAREA -> proyecto.eliminarPersonaDeTareaOptional(entrada.tituloTarea(), entrada.correoTrabajador());
            case MARCAR_TAREA_FINALIZADA -> proyecto.marcarTareaComoFinalizada(entrada.tituloTarea());
            case SALIR -> {
                return;
            }
        }
        herramienta(proyecto);
    }
}
