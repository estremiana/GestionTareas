package test.Facturacion;

import Modelo.Facturacion.ConsumoInterno;
import Modelo.Facturacion.Facturacion;
import org.junit.jupiter.api.Test;
import test.GeneralTest;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumoInternoTest extends GeneralTest {
    @Test
    void calcularcosteTest(){
       Facturacion prueba = new ConsumoInterno();
       float costeFinal = prueba.calcularCoste(tareaPrueba);

       imprimirResultadoYEsperado(costeFinal, 100);

       assertEquals(costeFinal, 100);
    }
}
