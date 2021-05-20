package Modelo;

import java.util.List;

public interface InterrogaModelo {
    String nombreProyecto();
    List<Tarea> getTareas();
    String[] getTiposResultado();

    Tarea identificarTarea(String titulo);

    String[] getTiposPrioridad();

    List<Persona> getPersonas();
}
