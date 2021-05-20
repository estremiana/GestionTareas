package Controlador;

import Modelo.CambioModelo;
import Modelo.Tarea;
import Vista.InterrogaVista;

public class ImplementacionControladorInicio implements ControladorInicio{
    private InterrogaVista vista;
    private CambioModelo modelo;

    @Override
    public void importar() {
        String fichero = vista.getUbicacionFichero();
        modelo.importarProyecto(fichero);
    }

    @Override
    public void nuevoProyecto() {
        String nuevoNombre = vista.getNuevoNombreProyecto();
        modelo.setNombreProyecto(nuevoNombre);
    }

    @Override
    public void nuevaTarea() {
        modelo.darDeAltaTareaVacia();

    }

    @Override
    public void guardarCambiosTarea() {

    }

    @Override
    public void tareaSeleccionada() {

    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }
}
