package Vista;

import Modelo.Serializacion;
import Controlador.GestionES;
import Vista.Menu.MenuFacturacion;
import Vista.Menu.MenuInicio;
import Modelo.Identificador;
import Vista.Menu.MenuHerramientas;
import Modelo.Menu.MenuResultado;
import Modelo.Proyecto;
import Modelo.Tarea;
import Modelo.Facturacion.*;

public class Interfaz {
    public Interfaz() {}

    GestionES entrada = new GestionES();
    GestionES salida = new GestionES();
    Proyecto proyecto;
    Identificador identificador = new Identificador();
    Serializacion serializacion = new Serializacion();

    public void inicio () {
        MenuInicio opcion = MenuInicio.getOpcion(entrada.indiceInicio());
        switch (opcion) {
            case CARGAR_PROYECTO -> {
                proyecto = serializacion.cargarDatosDeFichero();
                if (proyecto != null){
                    salida.cargaCorrecta(proyecto);
                    herramienta();
                }
            }
            case NUEVO_PROYECTO -> {
                proyecto = new Proyecto(entrada.nombreProyecto());
                herramienta();
            }
            case SALIR -> {
                return;
            }
        }
        inicio();
    }

    public void herramienta () {
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
            case ANADIR_FACTURACION -> {
                try {
                    Tarea tarea = identificador.tarea(entrada.tituloTarea(), proyecto.getTareas());
                    facturacion(tarea);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            case ANADIR_COSTE -> proyecto.anadirCosteATarea(entrada.tituloTarea(), entrada.coste());
            case SALIR -> { return; }
        }
        herramienta();
    }

    public void facturacion (Tarea tarea) {
        MenuFacturacion facturacion = MenuFacturacion.getOpcion(entrada.indiceFacturacion());
        switch (facturacion) {
            case CONSUMO_INTERNO -> tarea.setFacturacion(new ConsumoInterno());
            case DESCUENTO -> tarea.setFacturacion(new Descuento());
            case URGENTE -> tarea.setFacturacion(new Urgente());
        }
    }

}
