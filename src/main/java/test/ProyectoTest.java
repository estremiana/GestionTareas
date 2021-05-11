package test;

import Modelo.Persona;
import Modelo.Tarea;
import Modelo.Excepciones.PersonaNoPerteneceException;
import Modelo.Menu.MenuResultado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProyectoTest extends GeneralTest{

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


        tareasSolucion.add(tareaPrueba);


        proyectoPrueba.darDeAltaTarea("prueba", "desc", 0, MenuResultado.BIBLIOTECA, etiquetas);
        proyectoPrueba.getTareas().get(0).getListaPersonasAsignadas().add(dani);
        proyectoPrueba.getTareas().get(0).setResponsable(dani);

        imprimirResultadoYEsperado(proyectoPrueba.getTareas(), tareasSolucion);

        assertEquals(tareasSolucion, proyectoPrueba.getTareas());
    }

    @Test
    void anadirPersonaATarea() {
        proyectoPrueba.getTrabajadores().add(dani);
        proyectoPrueba.getTrabajadores().add(oscar);
        proyectoPrueba.getTareas().add(tareaPrueba);

        proyectoPrueba.anadirPersonaATarea("prueba", "oscar@gmail.com");
        List<Persona> sol = new ArrayList<>();
        sol.add(oscar);
        sol.add(dani);                                                                     //Con dani no se usa anadirPersonaATarea() pq ya está en la tarea

        List<Persona> listaPersonasTarea = new ArrayList<Persona>();
        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba"))
                listaPersonasTarea = tarea.getListaPersonasAsignadas();
        }

        imprimirResultadoYEsperado(sol, listaPersonasTarea);

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
        proyectoPrueba.getTrabajadores().add(dani);
        proyectoPrueba.getTrabajadores().add(oscar);
        proyectoPrueba.getTareas().add(tareaPrueba);

        List<Persona> sol = new ArrayList<>();
        sol.add(pablo);

        List<Persona> listaPersonasTarea = new ArrayList<>();
        for (Tarea tarea : proyectoPrueba.getTareas()) {
            if (tarea.getTitulo().equals("prueba")) {
                tarea.getListaPersonasAsignadas().add(oscar);
                tarea.getListaPersonasAsignadas().add(pablo);
            }
        }

        proyectoPrueba.eliminarPersonaDeTarea("prueba", "oscar@gmail.com");

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
        proyectoPrueba.getTrabajadores().add(dani);
        proyectoPrueba.getTrabajadores().add(oscar);
        proyectoPrueba.getTrabajadores().add(pablo);
        proyectoPrueba.getTareas().add(tareaPrueba);          //la tarea lleva como responsable a dani
        dani.asignarTarea(tareaPrueba);

        List<Persona> esperado = new ArrayList<Persona>();
        esperado.add(oscar);
        esperado.add(pablo);

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
    void comprobarAnadirResponsable() throws PersonaNoPerteneceException {
        tareaPruebaSinGente.getListaPersonasAsignadas().add(oscar);
        tareaPruebaSinGente.anadirResponsable(oscar);

        imprimirResultadoYEsperado(tareaPruebaSinGente.getResponsable(), oscar);
        assertEquals(tareaPruebaSinGente.getResponsable(), oscar);

        boolean resultado = tareaPruebaSinGente.getListaPersonasAsignadas().contains(oscar);
        imprimirResultadoYEsperado(true, resultado);
        assertTrue(resultado);

        resultado = oscar.getLista().contains(tareaPruebaSinGente);
        imprimirResultadoYEsperado(true, resultado);
        assertTrue(resultado);
    }

    @Test
    void comprobarEliminarPersona() throws PersonaNoPerteneceException {
        tareaPrueba.eliminarPersona(dani);

        imprimirResultadoYEsperado(tareaPrueba.getResponsable(), null);
        assertNull(tareaPrueba.getResponsable());

        boolean resultado = tareaPrueba.getListaPersonasAsignadas().contains(oscar);
        imprimirResultadoYEsperado(false, resultado);
        assertFalse(resultado);

        resultado = dani.getLista().contains(tareaPrueba);
        imprimirResultadoYEsperado(false, resultado);
        assertFalse(resultado);
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