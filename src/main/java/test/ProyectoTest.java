package test;

import Proyecto.*;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Menu.MenuResultado;
import Proyecto.Resultado.Biblioteca;

import Proyecto.Resultado.Documentacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProyectoTest {

    private Proyecto proyectoPrueba;
    private Tarea tareaPrueba, tareaPruebaSinGente;
    private List<String> etiquetas = new ArrayList<>();
    private Optional<Persona> dani = Optional.of(new Persona("Dani", "dani@gmail.com"));
    private Optional<Persona> oscar = Optional.of(new Persona("Oscar", "oscar@gmail.com"));
    private Optional<Persona> pablo = Optional.of(new Persona("Pablo", "pablo@gmail.com"));


    private static <E> void imprimirResultadoYEsperado(E resultado, E esperado) {
        System.out.println("Resultado:");
        System.out.println(resultado);
        System.out.println("Esperado:");
        System.out.println(esperado);
        System.out.println();
    }

    @BeforeEach
    void setUp() {
        proyectoPrueba = new Proyecto("Prueba");
        etiquetas.add("prueba");
        tareaPrueba = new Tarea("prueba", "desc", dani, MenuPrioridad.ALTA, new Biblioteca(), etiquetas);
        tareaPrueba.getListaPersonasAsignadas().add(dani.get());
        tareaPruebaSinGente = new Tarea("sinGente", "Tarea de prueba sin gente", Optional.empty(), MenuPrioridad.ALTA, new Documentacion(), etiquetas);
    }

    @Test
    @DisplayName("Comprobación listas vacías")
    void comprobarListas() {
        System.out.println("Comprobando que las listas están vacías al comienzo del proyecto");

        imprimirResultadoYEsperado(proyectoPrueba.getTrabajadores(), new ArrayList<Persona>());
        assertEquals(proyectoPrueba.getTrabajadores(), new ArrayList<Persona>());

        imprimirResultadoYEsperado(proyectoPrueba.getTareas(), new ArrayList<Tarea>());
        assertEquals(proyectoPrueba.getTareas(), new ArrayList<Tarea>());
    }

    @DisplayName("Dar de Alta Trabajador 1")
    @Test
    void darDeAltaTrabajadores() {
        System.out.println("Probando a dar de alta el trabajdor llamado Dani con correo dani@gmail.com");
        proyectoPrueba.darDeAltaTrabajador("Dani", "dani@gmail.com");

        List<Persona> sol = new ArrayList<>();
        sol.add(new Persona("Dani", "dani@gmail.com"));

        imprimirResultadoYEsperado(proyectoPrueba.getTrabajadores(), sol);



        System.out.println("Probando a dar de alta el trabajdor llamado Óscar con correo oscar@gmail.com");
        proyectoPrueba.darDeAltaTrabajador("Oscar", "oscar@gmail.com");

        sol.add(new Persona("Oscar", "oscar@gmail.com"));

        imprimirResultadoYEsperado(proyectoPrueba.getTrabajadores(), sol);

        assertEquals(sol, proyectoPrueba.getTrabajadores());
    }

    @Test
    void darDeAltaTarea() {

        List<Tarea> tareasSolucion = new ArrayList<Tarea>();

        proyectoPrueba.getTrabajadores().add(dani.get());     //se utiliza .get pq sabemos que no será empty
        tareasSolucion.add(tareaPrueba);

        proyectoPrueba.darDeAltaTarea("prueba", "desc", "dani@gmail.com", 0, MenuResultado.BIBLIOTECA, etiquetas);

        imprimirResultadoYEsperado(proyectoPrueba.getTareas(), tareasSolucion);

        assertEquals(tareasSolucion, proyectoPrueba.getTareas());
    }

    @Test
    void anadirPersonaATarea() {
        proyectoPrueba.getTrabajadores().add(dani.get());
        proyectoPrueba.getTrabajadores().add(oscar.get());
        proyectoPrueba.getTareas().add(tareaPrueba);

        proyectoPrueba.anadirPersonaATareaOptional("prueba", "oscar@gmail.com");
        List<Persona> sol = new ArrayList<>();
        sol.add(oscar.get());

        List<Persona> listaPersonasTarea = new ArrayList<Persona>();
        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba"))
                listaPersonasTarea = tarea.getListaPersonasAsignadas();
        }

        imprimirResultadoYEsperado(listaPersonasTarea, sol);

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
        proyectoPrueba.getTrabajadores().add(dani.get());
        proyectoPrueba.getTrabajadores().add(oscar.get());
        proyectoPrueba.getTareas().add(tareaPrueba);

        List<Persona> sol = new ArrayList<>();
        sol.add(pablo.get());

        List<Persona> listaPersonasTarea = new ArrayList<>();
        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                tarea.getListaPersonasAsignadas().add(oscar.get());
                tarea.getListaPersonasAsignadas().add(pablo.get());
            }
        }

        proyectoPrueba.eliminarPersonaDeTareaOptional("prueba", "oscar@gmail.com");

        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                listaPersonasTarea = tarea.getListaPersonasAsignadas();
            }
        }

        imprimirResultadoYEsperado(listaPersonasTarea, sol);

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
        proyectoPrueba.getTareas().add(tareaPrueba);

        proyectoPrueba.marcarTareaComoFinalizada("prueba");

        boolean resultado = false;

        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                resultado = tarea.isFinalizado();
                break;
            }
        }

        imprimirResultadoYEsperado(resultado, true);

        assertTrue(resultado); // para que esté finalizado ha de ser true

    }

    @Test
    void personasNoResponsables() {
        proyectoPrueba.getTrabajadores().add(dani.get());
        proyectoPrueba.getTrabajadores().add(oscar.get());
        proyectoPrueba.getTrabajadores().add(pablo.get());
        proyectoPrueba.getTareas().add(tareaPrueba);          //la tarea lleva como responsable a dani
        dani.get().asignarTarea(tareaPrueba);

        List<Persona> esperado = new ArrayList<Persona>();
        esperado.add(oscar.get());
        esperado.add(pablo.get());

        List<Persona> resultado = new ArrayList<Persona>();
        resultado = proyectoPrueba.listaPersonasNoResponsables(proyectoPrueba.getTrabajadores());

        imprimirResultadoYEsperado(resultado, esperado);

        assertEquals(esperado, resultado);


    }

    @Test
    void tareasSinPersonasAsignadas1() {
        List<Tarea> esperado = new ArrayList<Tarea>();

        List<Tarea> resultado = new ArrayList<Tarea>();
        resultado = proyectoPrueba.listaTareasSinPersonas();

        imprimirResultadoYEsperado(resultado, esperado);

        assertEquals(new ArrayList<Tarea>(), esperado);
    }

    @Test
    void tareasSinPersonasAsignadas2() {
        proyectoPrueba.getTareas().add(tareaPrueba);

        List<Tarea> esperado = new ArrayList<Tarea>();

        List<Tarea> resultado = new ArrayList<Tarea>();
        resultado = proyectoPrueba.listaTareasSinPersonas();

        imprimirResultadoYEsperado(resultado, esperado);

        assertEquals(new ArrayList<Tarea>(), esperado);
    }

    @Test
    void tareasSinPersonasAsignadas3() {
        proyectoPrueba.getTareas().add(tareaPruebaSinGente);

        List<Tarea> esperado = new ArrayList<Tarea>();
        esperado.add(tareaPruebaSinGente);

        List<Tarea> resultado = proyectoPrueba.listaTareasSinPersonas();

        imprimirResultadoYEsperado(resultado, esperado);

        assertEquals(resultado, esperado);
    }
}