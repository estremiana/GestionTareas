package test.Facturacion;

import Modelo.Facturacion.Descuento;
import Modelo.Persona;
import org.junit.jupiter.api.Test;
import test.GeneralTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoTest extends GeneralTest {
    @Test
    void calcularCosteConDescuentoDaniTest(){
        Descuento prueba = new Descuento();
        prueba.anadirPersonasConDescuento(dani);
        float costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 80);

        assertEquals(costeFinal, 80);
    }

    @Test
    void calcularCosteConDescuentoDaniOtroTest(){
        Descuento prueba = new Descuento();
        prueba.anadirPersonasConDescuento(new Persona("Dani", "dani@gmail.com"));
        float costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 80);

        assertEquals(costeFinal, 80);
    }

    @Test
    void calcularCosteConDescuentoOscarTest(){
        Descuento prueba = new Descuento();
        prueba.anadirPersonasConDescuento(dani);
        tareaPruebaSinGente.getListaPersonasAsignadas().add(oscar);
        tareaPruebaSinGente.setResponsable(oscar);
        tareaPruebaSinGente.setCoste(100);
        float costeFinal = prueba.calcularCoste(tareaPruebaSinGente);

        imprimirResultadoYEsperado(costeFinal, 100);

        assertEquals(costeFinal, 100);
    }
}
