package Proyecto.Menu.Herramientas;

import Proyecto.Proyecto;
import Proyecto.Persona;

public class ListarPersonasAsignadas extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        StringBuilder lista = new StringBuilder("Las personas asignadas al proyecto " + proyecto.getNombreProyecto() + " son:\n");
        for (Persona persona : proyecto.getPersonas()) {
            lista.append(persona.toString()).append("\n");
        }
        entrada.imprimirLista(lista.toString());
    }
}
