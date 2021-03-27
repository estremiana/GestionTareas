package Proyecto.Menu.Herramientas;

import Proyecto.Proyecto;
import Proyecto.Tarea;

import java.util.List;

public class MarcarTareaFinalizada extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        List<Tarea> tareas = proyecto.getTareas();
        String tituloTarea = entrada.tituloTarea();
        identificar.tarea(tituloTarea, tareas).ifPresent(Tarea::finalizar);

    }
}
