package Main;


import Controlador.ImplementacionControladorInicio;
import Modelo.Proyecto;
import Vista.View;
import com.formdev.flatlaf.IntelliJTheme;

public class Principal {
    public static void main(String[] args) {
        ImplementacionControladorInicio controlador = new ImplementacionControladorInicio();
        View vista = new View();
        Proyecto modelo = new Proyecto();

        controlador.setModeloProyecto(modelo);
        controlador.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        modelo.setVista(vista);

        IntelliJTheme.setup( Principal.class.getClassLoader().getResourceAsStream(      //Para dejarlo normal solo hay que comentar estas dos lineas
                "themes/Carbon.theme.json"));

        vista.creaInicio();
    }
}
