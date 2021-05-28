package Modelo.Interfaces;

import Modelo.Persona;
import Modelo.Tarea;

import java.util.List;

public interface InterrogaModelo {
    String nombreProyecto();
    List<Tarea> getTareas();
    String[] getTiposResultado();

    Tarea identificarTarea(String titulo);

    String[] getTiposPrioridad();

    List<Persona> getPersonas();

    String[] getTiposFacturacion();

    List<String> getTitulosTareas();

    Persona identificarPersona(String correo) ;
}
