package Proyecto.Menu.Herramientas;

import Proyecto.Identificador;
import Proyecto.Persona;
import Proyecto.Proyecto;
import Proyecto.Tarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnadirPersonaATarea extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        Optional<Tarea> tarea = identificar.tarea(entrada.tituloTarea(), proyecto.getTareas());
        Optional<Persona> persona = identificar.personaOpcional(entrada.correoTrabajador(), proyecto.getPersonas());
        tarea.ifPresent(tarea1 -> persona.ifPresent(tarea1::añadirPersona));
    }


}
