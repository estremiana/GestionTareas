package test.Facturacion;

import Modelo.Facturacion.Descuento;
import org.junit.jupiter.api.Test;
import test.GeneralTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoTest extends GeneralTest {
    @Test
    void calcularCosteConDescuentoDaniTest(){
        dani.setDescuento(true);
        tareaPrueba.setFacturacion(new Descuento());
        float costeFinalResultado = tareaPrueba.calcularCosteFinal();
        float costeFinalEsperado = 80;


        //Descuento prueba = new Descuento();
        //prueba.anadirPersonasConDescuento(dani);
        //float costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinalResultado, costeFinalEsperado);

        assertEquals(costeFinalResultado, costeFinalEsperado);
    }

    @Test
    void calcularCosteConDescuentoDaniOtroTest(){
        tareaPrueba.setFacturacion(new Descuento());
        float costeFinalResultrado = tareaPrueba.calcularCosteFinal();

        imprimirResultadoYEsperado(costeFinalResultrado, 100);

        assertEquals(costeFinalResultrado, 100);
    }

    @Test
    void calcularCosteConDescuentoOscarTest(){
        dani.setDescuento(true);
        tareaPruebaSinGente.setFacturacion(new Descuento());
        tareaPruebaSinGente.getListaPersonasAsignadas().add(oscar);
        tareaPruebaSinGente.setResponsable(oscar);
        tareaPruebaSinGente.setCoste(100);
        float costeFinalResultado = tareaPruebaSinGente.calcularCosteFinal();

        imprimirResultadoYEsperado(costeFinalResultado, 100);

        assertEquals(costeFinalResultado, 100);
    }
}
