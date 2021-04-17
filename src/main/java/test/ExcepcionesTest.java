package test;

import Proyecto.Excepciones.PersonaNoPerteneceException;
import Proyecto.Excepciones.PersonaYaPerteneceException;
import Proyecto.Identificador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExcepcionesTest extends GeneralTest{

    Identificador identificador = new Identificador();

    //PROYECTO
    @Test
    public void noRepetidosPersonasEnTarea() {          //tareaPrueba solo tiene a dani como persona asignada
        assertThrows(PersonaYaPerteneceException.class, () -> tareaPrueba.anadirPersona(dani));
        assertDoesNotThrow(() -> tareaPrueba.anadirPersona(oscar));
    }

    @Test
    public void asignarResponsableSinAnadirPersona() {  //tareaPrueba solo tiene a dani como persona asignada, pero no como responsable
        assertThrows(PersonaNoPerteneceException.class, () -> tareaPrueba.anadirResponsable(oscar));
        assertDoesNotThrow(() -> tareaPrueba.anadirResponsable(dani));
    }

    @Test
    public void anadirTrabajadorRepetido() {  //tareaPrueba solo tiene a dani como persona asignada, pero no como responsable
        proyectoPrueba.getTrabajadores().add(dani);

        assertThrows(PersonaYaPerteneceException.class, () -> proyectoPrueba.anadirTrabajador(dani));
        assertDoesNotThrow(() -> proyectoPrueba.anadirTrabajador(oscar));
    }

    @Test
    public void eliminarPersonaDeTareaSinEstarAsignada () {
        assertThrows(PersonaNoPerteneceException.class, () -> tareaPrueba.eliminarPersona(oscar));
        assertDoesNotThrow(() -> tareaPrueba.eliminarPersona(dani));
    }


    //IDENTIFICADOR

    @Test
    public void identificarTareaQueNoExiste() {
        assertThrows(IllegalArgumentException.class, () -> identificador.tarea("ete proyecto no tiene tareas", proyectoPrueba.getTareas()));

        proyectoPrueba.getTareas().add(tareaPrueba);
        assertThrows(IllegalArgumentException.class, () -> identificador.tarea("esta tarea no esta en el proyecto", proyectoPrueba.getTareas()));
        assertDoesNotThrow(() -> identificador.tarea("prueba", proyectoPrueba.getTareas()));
    }

    @Test
    public void identificarPersonaQueNoExiste() {
        proyectoPrueba.getTrabajadores().add(dani);
        assertThrows(PersonaNoPerteneceException.class, () ->identificador.persona("oscar@gmail.com", proyectoPrueba.getTrabajadores()));
        assertDoesNotThrow(() -> identificador.persona("dani@gmail.com", proyectoPrueba.getTrabajadores()));
    }
}
