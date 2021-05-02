package test.Facturacion;

import Proyecto.Facturacion.Descuento;
import Proyecto.Facturacion.Facturacion;
import Proyecto.Menu.MenuPrioridad;
import org.junit.jupiter.api.Test;
import test.GeneralTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrgenteTest extends GeneralTest {
    @Test
    void calcularCosteConUrgenciaBajaTest(){
        Facturacion prueba = new Descuento();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.BAJA);
        int costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 100);

        assertEquals(costeFinal, 100);
    }

    @Test
    void calcularCosteConUrgenciaMediaTest(){
        Facturacion prueba = new Descuento();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.MEDIA);
        int costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 110);

        assertEquals(costeFinal, 110);
    }

    @Test
    void calcularCosteConUrgenciaAltaTest(){
        Facturacion prueba = new Descuento();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.ALTA);
        int costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 120);

        assertEquals(costeFinal, 120);
    }
}
