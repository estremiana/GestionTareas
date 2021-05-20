package Main;


import Controlador.ImplementacionControladorInicio;
import Modelo.Menu.MenuPrioridad;
import Modelo.Persona;
import Modelo.Proyecto;
import Modelo.ModeloTabla;
import Modelo.Resultado.Biblioteca;
import Modelo.Resultado.PaginaWeb;
import Modelo.Tarea;
import Vista.Inicio;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        //Interfaz interfaz = new Interfaz();
        //interfaz.inicio();
        Persona dani = new Persona("dani", "daniemail");
        Persona oscar = new Persona("oscar", "oscaremail");

        ImplementacionControladorInicio controlador = new ImplementacionControladorInicio();
        Inicio vista = new Inicio();
        Proyecto modelo = new Proyecto();
        List<Tarea> prueba = new ArrayList<Tarea>();
        Tarea tarea = new Tarea("prueba", "hola", MenuPrioridad.ALTA, new PaginaWeb(), new ArrayList<>());
        tarea.getPersonasAsignadas().add(dani);
        tarea.setResponsable(dani);
        prueba.add(tarea);
        tarea = new Tarea("prueba2", "ha", MenuPrioridad.BAJA, new Biblioteca(), new ArrayList<>());
        tarea.getPersonasAsignadas().add(oscar);
        tarea.setResponsable(oscar);
        modelo.getPersonas().add(dani);
        modelo.getPersonas().add(oscar);
        modelo.getPersonas().add(new Persona("pablo", "pabloemail"));
        prueba.add(tarea);
        modelo.setTareas(prueba);
        //ModeloTabla modeloTabla = new ModeloTabla(datos);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        //vista.setModeloTabla(modeloTabla);
        modelo.setVista(vista);

        vista.creaInicio();
    }
}
