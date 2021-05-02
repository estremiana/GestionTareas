package test.Facturacion;

import Proyecto.Facturacion.ConsumoInterno;
import Proyecto.Facturacion.Descuento;
import Proyecto.Facturacion.Facturacion;
import org.junit.jupiter.api.Test;
import test.GeneralTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoTest extends GeneralTest {
    @Test
    void calcularCosteConDescuentoDaniTest(){
        Facturacion prueba = new Descuento();
        int costeFinal = prueba.calcularCoste(tareaPrueba);

        imprimirResultadoYEsperado(costeFinal, 80);

        assertEquals(costeFinal, 80);
    }

    @Test
    void calcularCosteConDescuentoOscarTest(){
        Facturacion prueba = new Descuento();
        tareaPruebaSinGente.getListaPersonasAsignadas().add(oscar);
        tareaPruebaSinGente.setResponsable(oscar);
        int costeFinal = prueba.calcularCoste(tareaPruebaSinGente);

        imprimirResultadoYEsperado(costeFinal, 100);

        assertEquals(costeFinal, 100);
    }
}
