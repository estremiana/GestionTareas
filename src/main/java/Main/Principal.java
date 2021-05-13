package Main;


import Controlador.ImplementacionControladorInicio;
import Modelo.Proyecto;
import Vista.Inicio;

public class Principal {
    public static void main(String[] args) {
        //Interfaz interfaz = new Interfaz();
        //interfaz.inicio();
        ImplementacionControladorInicio controladorInicio = new ImplementacionControladorInicio();
        Inicio vista = new Inicio();
        Proyecto proyecto = new Proyecto();
        controladorInicio.setModelo(proyecto);
        controladorInicio.setVista(vista);
        vista.setControlador(controladorInicio);
        proyecto.setVista(vista);

        vista.creaInicio();
    }


}
