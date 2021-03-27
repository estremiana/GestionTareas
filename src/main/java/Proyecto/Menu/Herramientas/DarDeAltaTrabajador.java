package Proyecto.Menu.Herramientas;

import Proyecto.Persona;
import Proyecto.Proyecto;

public class DarDeAltaTrabajador extends Herramienta {

    @Override
    public void accion(Proyecto proyecto) {
        proyecto.getPersonas().add(new Persona(entrada.nombreTrabajador(), entrada.correoTrabajador()));
    }
}
