package Proyecto.Menu.Herramientas;

import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Proyecto;
import Proyecto.Resultado.Resultado;
import Proyecto.Tarea;
import Proyecto.Persona;


import java.util.ArrayList;
import java.util.List;

public class DarDeAltaTarea extends Herramienta {
    @Override
    public void accion(Proyecto proyecto) {
        List<Persona> trabajadores = proyecto.getPersonas();
        String titulo = entrada.tituloTarea();
        String descripcion = entrada.descripcionTarea();
        Persona responsable = identificar.personaOpcional(entrada.correoPersonaResponsableTarea(), trabajadores).orElse(new Persona());
        List<Persona> personasAsignadas = pedirPersonasAsignadas(trabajadores);
        MenuPrioridad prioridad = MenuPrioridad.getOpcion(entrada.prioridad());
        Resultado resultado = identificar.resultado(MenuResultado.getOpcion(entrada.resultado()));
        List<String> etiquetas = entrada.etiquetasTarea();
        Tarea nuevaTarea = new Tarea(titulo, descripcion, personasAsignadas, responsable, prioridad, resultado, etiquetas);
        proyecto.getTareas().add(nuevaTarea);
        responsable.asignarTarea(nuevaTarea);
    }

    private List<Persona> pedirPersonasAsignadas(List<Persona> personas) {
        List<Persona> personasAsignadas = new ArrayList<>();
        for (String correo : entrada.correosPersonasAsignadasTarea())
            identificar.personaOpcional(correo, personas).ifPresent(personasAsignadas::add);    //Añade solo cuando la persona no es empty
        return personasAsignadas;
    }
}
