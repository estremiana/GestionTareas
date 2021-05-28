package Controlador;

import Modelo.Interfaces.CambioModeloProyecto;
import Modelo.Interfaces.CambioModeloPersona;
import Modelo.Interfaces.CambioModeloTarea;
import Vista.InterrogaVista;

import java.io.Serializable;
import java.util.Map;

public class ImplementacionControladorInicio implements ControladorInicio, Serializable {
    private InterrogaVista vista;
    private CambioModeloProyecto modeloProyecto;
    private CambioModeloTarea modeloTarea;
    private CambioModeloPersona modeloPersona;

    @Override
    public void importar() {
        String fichero = vista.getUbicacionFichero();
        modeloProyecto.importarProyecto(fichero);
    }

    @Override
    public void exportar() {
        String fichero = vista.getUbicacionFichero();
        modeloProyecto.exportarProyecto(fichero);
    }

    @Override
    public void nuevoProyecto() {
        String nuevoNombre = vista.getNuevoNombreProyecto();
        if (nuevoNombre != null)
            modeloProyecto.setNombreProyecto(nuevoNombre);
    }

    @Override
    public void nuevaTarea() {
        Map<String, String> datosNuevaTarea = vista.getDatosNuevaTarea();
        if (datosNuevaTarea != null)
            modeloProyecto.darDeAltaTarea(datosNuevaTarea.get("Titulo"),
                    datosNuevaTarea.get("Descripcion"),
                    datosNuevaTarea.get("Prioridad"),
                    datosNuevaTarea.get("Resultado"),
                    datosNuevaTarea.get("Coste"));
    }

    @Override
    public void guardarCambiosTarea() {
        modeloTarea = vista.getTareaActual();
        modeloTarea.setDescripcion(vista.getNuevoDescripcion());
        modeloTarea.setResponsable(vista.getNuevoResponsable());
        modeloTarea.setPersonasAsignadas(vista.getNuevoPersonasAsignadas());
        modeloTarea.setNuevaPrioridad(vista.getNuevoPrioridad());
        modeloTarea.setFinalizado(vista.getNuevoFinalizado());
        modeloTarea.setResultado(vista.getNuevoResultado());
        modeloTarea.setEtiquetas(vista.getNuevoListaEtiquetas());
        modeloTarea.setCoste(vista.getNuevoCoste());
        modeloTarea.setNuevoFacturacion(vista.getNuevoFacturacion());
        modeloTarea.datosActualizados();
    }

    @Override
    public void guardarCambiosPersona() {
        modeloPersona = vista.getPersonaActual();
        modeloPersona.setDescuento(vista.getNuevoDescuento());
        modeloPersona.datosActualizados();
    }

    @Override
    public void nuevaPersona() {
        Map<String, String> datosPersona = vista.getDatosNuevaPersona();
        if (datosPersona != null)
            modeloProyecto.nuevoTrabajador(datosPersona.get("Nombre"),
                    datosPersona.get("Correo"));
    }



    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    public void setModeloProyecto(CambioModeloProyecto modeloProyecto) {
        this.modeloProyecto = modeloProyecto;
    }
}
