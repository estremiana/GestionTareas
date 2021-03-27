package Proyecto.Menu.Herramientas;

import Proyecto.Proyecto;
import Proyecto.Tarea;

public class ListarTareas extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        StringBuilder lista = new StringBuilder();
        lista.append("Las tareas del proyecto ").append(proyecto.getNombreProyecto()).append(" son: \n");
        for (Tarea tarea : proyecto.getTareas()) {
            lista.append(tarea.toString());
        }
        entrada.imprimirLista(lista.toString());
    }
}
