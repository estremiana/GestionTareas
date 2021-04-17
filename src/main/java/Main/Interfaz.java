package Main;

import Proyecto.Identificador;
import Proyecto.Menu.MenuHerramientas;
import Proyecto.Menu.MenuResultado;
import Proyecto.Proyecto;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class Interfaz {
    public Interfaz() {}

    GestionES entrada = new GestionES();
    GestionES salida = new GestionES();
    Identificador identificador = new Identificador();
    Serializacion serializacion = new Serializacion();

    public void herramienta (Proyecto proyecto) {
        MenuHerramientas accion = MenuHerramientas.getOpcion(entrada.indiceHerramienta());
        switch (accion) {
            case DAR_DE_ALTA_TRABAJADOR -> {
                String nombre = entrada.nombreTrabajador();
                String correo = entrada.correoTrabajador();
                proyecto.darDeAltaTrabajador(nombre, correo);
            }
            case DAR_DE_ALTA_TAREA -> proyecto.darDeAltaTarea(entrada.tituloTarea(), entrada.descripcionTarea(), identificador.prioridad(entrada.prioridad()), MenuResultado.getOpcion(entrada.resultado()), entrada.etiquetasTarea());
            case LISTAR_TAREAS -> salida.listarPorPantalla(proyecto.listarTareas());
            case LISTAR_PERSONAS_ASIGNADAS -> salida.listarPorPantalla(proyecto.listarPersonasAsignadasProyecto());
            case ANADIR_PERSONA_A_TAREA -> proyecto.anadirPersonaATarea(entrada.tituloTarea(), entrada.correoTrabajador());
            case ANADIR_RESPONSABLE_A_TAREA -> proyecto.asignarResponsableATarea(entrada.tituloTarea(), entrada.correoPersonaResponsableTarea());
            case ELIMINAR_PERSONA_TAREA -> proyecto.eliminarPersonaDeTarea(entrada.tituloTarea(), entrada.correoTrabajador());
            case MARCAR_TAREA_FINALIZADA -> proyecto.marcarTareaComoFinalizada(entrada.tituloTarea());
            case CARGAR_DATOS_DESDE_ARCHIVO -> proyecto = serializacion.cargarDatosDeFichero(proyecto);
            case GUARDAR_DATOS_A_ARCHIVO -> serializacion.guardarDatosAFichero(proyecto);
            case SALIR -> {
                return;
            }
        }
        herramienta(proyecto);
    }
}
