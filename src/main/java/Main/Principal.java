package Main;

import Proyecto.Proyecto;

public class Principal {
    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("tupu");
        Interfaz interfaz = new Interfaz();
        interfaz.herramienta(proyecto);
        //prueba.elegirHerrramienta();
        //prueba.darDeAltaTrabajador("Daniel Estremiana", "daniestremiana@gmail.com");
        //prueba.darDeAltaTrabajador("Óscar Monedero", "oscarmonedero@gmail.com");
        //prueba.darDeAltaTrabajador("Pablo Pitarch", "ppitarch@gmail.com");



        //prueba.anadirPersonaATarea("ppitarch@gmail.com", "Acabar esto");
        //System.out.println(prueba.listarPersonasAsignadasProyecto());
        //prueba.marcarTareaComoFinalizada("Acabar esto");
        //System.out.println(prueba.listarTareas());
    }
}
