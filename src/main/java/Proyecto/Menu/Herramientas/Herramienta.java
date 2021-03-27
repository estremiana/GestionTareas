package Proyecto.Menu.Herramientas;

import Proyecto.Identificador;
import Proyecto.Proyecto;
import Proyecto.GestionES;

public abstract class Herramienta {
    public Identificador identificar = new Identificador();
    public GestionES entrada = new GestionES();
    public abstract void accion(Proyecto proyecto);

}
