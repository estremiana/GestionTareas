package test;

import Modelo.Persona;
import Modelo.Proyecto;
import Modelo.Tarea;


import Modelo.Menu.MenuPrioridad;
import Modelo.Resultado.Biblioteca;
import Modelo.Resultado.Documentacion;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneralTest {
    public Proyecto proyectoPrueba;
    public Tarea tareaPrueba, tareaPruebaSinGente;
    public String etiquetas;
    public Persona dani = new Persona("Dani", "dani@gmail.com");
    public Persona oscar = new Persona("Oscar", "oscar@gmail.com");
    public Persona pablo = new Persona("Pablo", "pablo@gmail.com");


    @BeforeEach
    void setUp() {
        proyectoPrueba = new Proyecto("Prueba");
        etiquetas = ("prueba");
        tareaPrueba = new Tarea("prueba", "desc",MenuPrioridad.ALTA, new Biblioteca(), etiquetas);
        tareaPrueba.setCoste(100);
        tareaPrueba.getListaPersonasAsignadas().add(dani);
        tareaPrueba.setResponsable(dani);
        dani.getLista().add(tareaPrueba);
        tareaPruebaSinGente = new Tarea("sinGente", "Tarea de prueba sin gente", MenuPrioridad.ALTA, new Documentacion(), etiquetas);
    }

    public static <E> void imprimirResultadoYEsperado(E resultado, E esperado) {
        System.out.println("Resultado:");
        System.out.println(resultado);
        System.out.println("Esperado:");
        System.out.println(esperado);
        System.out.println();
    }

}
