package Test;

import Proyecto.Menu.MenuHerramientas;
import Proyecto.Proyecto;
import Proyecto.Persona;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
class ProyectoTest {

    private Proyecto proyecto;

    private InputStream entrada;

    @org.junit.jupiter.api.Test
    void darDeAltaTrabajador() {
        proyecto = new Proyecto("hola");
        MenuHerramientas.DAR_DE_ALTA_TRABAJADOR.accion(proyecto);
        assertEquals(proyecto.getPersonas().get(0), new Persona());
    }

    @org.junit.jupiter.api.Test
    void darDeAltaTarea() {
    }

    @org.junit.jupiter.api.Test
    void listarTareas() {
    }

    @org.junit.jupiter.api.Test
    void listarPersonasAsignadasProyecto() {
    }

    @org.junit.jupiter.api.Test
    void anadirPersonaATareaOptional() {
    }

    @org.junit.jupiter.api.Test
    void eliminarPersonaDeTareaOptional() {
    }

    @org.junit.jupiter.api.Test
    void marcarTareaComoFinalizada() {
    }
}