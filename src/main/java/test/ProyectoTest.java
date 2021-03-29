package test;

import Proyecto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProyectoTest {

    private Proyecto proyecto;

    @BeforeEach
    void setUp() {
        proyecto = new Proyecto("Prueba");
    }

    @DisplayName("Dar de Alta Trabajador 1")
    @Test
    void darDeAltaTrabajadores() {
        System.out.println("Probando a dar de alta el trabajdor llamado Dani con correo dani@gmail.com");
        proyecto.darDeAltaTrabajador("Dani", "dani@gmail.com");

        List<Persona> sol = new ArrayList<>();
        sol.add(new Persona("Dani", "dani@gmail.com"));

        System.out.println("Resultado:");
        System.out.println(proyecto.getTrabajadores());
        System.out.println("Esperado:");
        System.out.println(sol + "\n");
        assertEquals(sol, proyecto.getTrabajadores());


        System.out.println("Probando a dar de alta el trabajdor llamado Óscar con correo oscar@gmail.com");
        proyecto.darDeAltaTrabajador("Oscar", "oscar@gmail.com");

        sol.add(new Persona("Oscar", "oscar@gmail.com"));

        System.out.println("Resultado:");
        System.out.println(proyecto.getTrabajadores());
        System.out.println("Esperado:");
        System.out.println(sol + "\n");
        assertEquals(sol, proyecto.getTrabajadores());
    }

    @Test
    void darDeAltaTarea() {
    }

    @Test
    void listarTareas() {
    }

    @Test
    void listarPersonasAsignadasProyecto() {
    }

    @Test
    void anadirPersonaATareaOptional() {
    }

    @Test
    void eliminarPersonaDeTareaOptional() {
    }

    @Test
    void marcarTareaComoFinalizada() {
    }
}