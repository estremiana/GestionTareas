package Proyecto.Menu.Herramientas;

import Proyecto.Proyecto;
import Proyecto.Tarea;
import Proyecto.Persona;

import java.util.Optional;

public class EliminarPersonaTarea extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        Optional<Tarea> tarea = identificar.tarea(entrada.tituloTarea(), proyecto.getTareas());
        Optional<Persona> persona = identificar.personaOpcional(entrada.correoTrabajador(), proyecto.getPersonas());
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::eliminarPersona));
    }
}
