package Main;

import Main.Menu.MenuInicio;
import Proyecto.Identificador;
import Main.Menu.MenuHerramientas;
import Proyecto.Menu.MenuResultado;
import Proyecto.Proyecto;

public class Interfaz {
    public Interfaz() {}

    GestionES entrada = new GestionES();
    GestionES salida = new GestionES();
    Identificador identificador = new Identificador();
    Serializacion serializacion = new Serializacion();

    public void inicio () {
        MenuInicio opcion = MenuInicio.getOpcion(entrada.indiceInicio());
        switch (opcion) {
            case CARGAR_PROYECTO -> {
                Proyecto proyecto = serializacion.cargarDatosDeFichero();
                if (proyecto != null){
                    salida.cargaCorrecta(proyecto);
                    herramienta(proyecto);
                }
            }
            case NUEVO_PROYECTO -> herramienta(new Proyecto(entrada.nombreProyecto()));
            case SALIR -> {
                return;
            }
        }
        inicio();
    }

    public void herramienta (Proyecto proyecto) {
        MenuHerramientas accion = MenuHerramientas.getOpcion(entrada.indiceHerramienta());
        switch (accion) {
            case DAR_DE_ALTA_TRABAJADOR -> proyecto.darDeAltaTrabajador(entrada.nombreTrabajador(), entrada.correoTrabajador());
            case DAR_DE_ALTA_TAREA -> proyecto.darDeAltaTarea(entrada.tituloTarea(), entrada.descripcionTarea(), identificador.prioridad(entrada.prioridad()), MenuResultado.getOpcion(entrada.resultado()), entrada.etiquetasTarea());
            case LISTAR_TAREAS -> salida.listarPorPantalla(proyecto.listarTareas());
            case LISTAR_PERSONAS_ASIGNADAS -> salida.listarPorPantalla(proyecto.listarPersonasAsignadasProyecto());
            case ANADIR_PERSONA_A_TAREA -> proyecto.anadirPersonaATarea(entrada.tituloTarea(), entrada.correoTrabajador());
            case ANADIR_RESPONSABLE_A_TAREA -> proyecto.asignarResponsableATarea(entrada.tituloTarea(), entrada.correoPersonaResponsableTarea());
            case ELIMINAR_PERSONA_TAREA -> proyecto.eliminarPersonaDeTarea(entrada.tituloTarea(), entrada.correoTrabajador());
            case MARCAR_TAREA_FINALIZADA -> proyecto.marcarTareaComoFinalizada(entrada.tituloTarea());
            case GUARDAR_DATOS_A_ARCHIVO -> serializacion.guardarDatosAFichero(proyecto);
            case ANADIR_FACTURACION -> ;
            case SALIR -> { return; }
        }
        herramienta(proyecto);
    }

    public void facturacion (Proyecto proyecto) {

    }

}
