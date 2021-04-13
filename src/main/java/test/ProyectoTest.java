package test;

import Proyecto.*;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.Biblioteca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProyectoTest {

    private Proyecto proyecto;
    private Tarea tareaPrueba;
    private List<String> etiquetas = new ArrayList<>();
    private Optional<Persona> dani = Optional.of(new Persona("Dani", "dani@gmail.com"));
    private Optional<Persona> oscar = Optional.of(new Persona("Oscar", "oscar@gmail.com"));
    private Optional<Persona> pablo = Optional.of(new Persona("Pablo", "pablo@gmail.com"));


    @BeforeEach
    void setUp() {
        proyecto = new Proyecto("Prueba");
        etiquetas.add("prueba");
        tareaPrueba = new Tarea("prueba", "desc", dani, MenuPrioridad.ALTA, new Biblioteca(), etiquetas);
    }

    @Test
    @DisplayName("Comprobación listas vacías")
    void comprobarListas() {
        System.out.println("Comprobando que las listas están vacías al comienzo del proyecto");


        System.out.println("Resultado trabajadores:");
        System.out.println(proyecto.getTrabajadores());
        System.out.println("Esperado:");
        System.out.println(new ArrayList<Persona>() + "\n");
        assertEquals(proyecto.getTrabajadores(), new ArrayList<Persona>());

        System.out.println("Resultado tareas:");
        System.out.println(proyecto.getTrabajadores());
        System.out.println("Esperado:");
        System.out.println(new ArrayList<Tarea>() + "\n");
        assertEquals(proyecto.getTareas(), new ArrayList<Tarea>());
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

        List<Tarea> tareasSolucion = new ArrayList<Tarea>();

        proyecto.getTrabajadores().add(dani.get());     //se utiliza .get pq sabemos que no será empty
        tareasSolucion.add(tareaPrueba);

        proyecto.darDeAltaTarea("prueba", "desc", "dani@gmail.com", 0, MenuResultado.BIBLIOTECA, etiquetas);

        System.out.println("Resultado:");
        System.out.println(proyecto.getTareas());
        System.out.println("Esperado:");
        System.out.println(tareasSolucion + "\n");

        assertEquals(tareasSolucion, proyecto.getTareas());
    }

    //@Test
    //void listarTareas() {
    //}

    //@Test
    //void listarPersonasAsignadasProyecto() {
    //}

    @Test
    void anadirPersonaATarea() {
        proyecto.getTrabajadores().add(dani.get());
        proyecto.getTrabajadores().add(oscar.get());
        proyecto.getTareas().add(tareaPrueba);

        proyecto.anadirPersonaATareaOptional("prueba", "oscar@gmail.com");
        List<Persona> sol = new ArrayList<>();
        sol.add(oscar.get());

        List<Persona> listaPersonasTarea = new ArrayList<Persona>();
        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getTitulo().equals("prueba"))
                listaPersonasTarea = tarea.getListaPersonasAsignadas();
        }

        System.out.println("Resultado:");
        System.out.println(listaPersonasTarea);
        System.out.println("Esperado:");
        System.out.println(sol + "\n");

        boolean resultado = true;
        for (Persona persona : sol) {
            if (!listaPersonasTarea.contains(persona)) {
                resultado = false;
                break;
            }
        }
        assertTrue(resultado);
    }

    @Test
    void eliminarPersonaDeTarea() {
        proyecto.getTrabajadores().add(dani.get());
        proyecto.getTrabajadores().add(oscar.get());
        proyecto.getTareas().add(tareaPrueba);

        List<Persona> sol = new ArrayList<>();
        sol.add(pablo.get());

        List<Persona> listaPersonasTarea = new ArrayList<>();
        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                tarea.getListaPersonasAsignadas().add(oscar.get());
                tarea.getListaPersonasAsignadas().add(pablo.get());
            }
        }

        proyecto.eliminarPersonaDeTareaOptional("prueba", "oscar@gmail.com");

        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                listaPersonasTarea = tarea.getListaPersonasAsignadas();
            }
        }

        System.out.println("Resultado:");
        System.out.println(listaPersonasTarea);
        System.out.println("Esperado:");
        System.out.println(sol + "\n");

        boolean resultado = true;
        for (Persona persona : sol) {
            if (!listaPersonasTarea.contains(persona)) {
                resultado = false;
                break;
            }
        }
        assertTrue(resultado);
    }

    @Test
    void marcarTareaComoFinalizada() {
        proyecto.getTareas().add(tareaPrueba);

        proyecto.marcarTareaComoFinalizada("prueba");

        boolean resultado = false;

        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                resultado = tarea.isFinalizado();
                break;
            }
        }

        System.out.println("Resultado:");
        System.out.println(resultado);
        System.out.println("Esperado:");
        System.out.println(true + "\n");

        assertTrue(resultado); // para que esté finalizado ha de ser true

    }

    @Test
    void personasNoResponsables() {
        proyecto.getTrabajadores().add(dani.get());
        proyecto.getTrabajadores().add(oscar.get());
        proyecto.getTrabajadores().add(pablo.get());
        proyecto.getTareas().add(tareaPrueba);          //la tarea lleva como responsable a dani

        List<Persona> esperado = new ArrayList<Persona>();
        esperado.add(oscar.get());
        esperado.add(pablo.get());

        assertEquals(esperado, listaPersonasNoResponsables());
    }
}