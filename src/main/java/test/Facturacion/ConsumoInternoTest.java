package test.Facturacion;

import Proyecto.Facturacion.ConsumoInterno;
import Proyecto.Facturacion.Facturacion;
import org.junit.jupiter.api.Test;
import test.GeneralTest;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumoInternoTest extends GeneralTest {
    @Test
    void calcularcosteTest(){
       Facturacion prueba = new ConsumoInterno();
       int costeFinal = prueba.calcularCoste(tareaPrueba);

       imprimirResultadoYEsperado(costeFinal, 100);

       assertEquals(costeFinal, 100);
    }
}
