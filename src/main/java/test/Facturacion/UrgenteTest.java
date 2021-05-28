package test.Facturacion;

import Modelo.Facturacion.Facturacion;
import Modelo.Facturacion.Urgente;
import Modelo.Menu.MenuPrioridad;
import org.junit.jupiter.api.Test;
import test.GeneralTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrgenteTest extends GeneralTest {
    @Test
    void calcularCosteConUrgenciaBajaTest(){
        Facturacion prueba = new Urgente();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.BAJA);
        float costeFinal = prueba.calcularCoste(tareaPruebaSinGente);

        imprimirResultadoYEsperado(costeFinal, 110);

        assertEquals(costeFinal, 110);
    }

    @Test
    void calcularCosteConUrgenciaMediaTest(){
        Facturacion prueba = new Urgente();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.MEDIA);
        float costeFinal = prueba.calcularCoste(tareaPruebaSinGente);

        imprimirResultadoYEsperado(costeFinal, 120);

        assertEquals(costeFinal, 120);
    }

    @Test
    void calcularCosteConUrgenciaAltaTest(){
        Facturacion prueba = new Urgente();
        tareaPruebaSinGente.setCoste(100);
        tareaPruebaSinGente.setPrioridad(MenuPrioridad.ALTA);
        float costeFinal = prueba.calcularCoste(tareaPruebaSinGente);

        imprimirResultadoYEsperado(costeFinal, 130);

        assertEquals(costeFinal, 130);
    }
}
