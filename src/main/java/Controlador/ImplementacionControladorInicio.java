package Controlador;

import Modelo.CambioModelo;
import Vista.InterrogaVista;

public class ImplementacionControladorInicio implements ControladorInicio{
    private InterrogaVista vista;
    private CambioModelo modelo;

    @Override
    public void importar() {
        String fichero = vista.getEntrada();
        modelo.importarProyecto(fichero);
    }

    @Override
    public void nuevo() {

    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }
}
