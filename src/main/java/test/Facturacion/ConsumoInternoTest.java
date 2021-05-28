package test.Facturacion;

import Modelo.Facturacion.ConsumoInterno;
import org.junit.jupiter.api.Test;
import test.GeneralTest;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumoInternoTest extends GeneralTest {
    @Test
    void calcularcosteTest(){
        tareaPrueba.setFacturacion(new ConsumoInterno());
        float costeFinalResultado = tareaPrueba.calcularCosteFinal();

        imprimirResultadoYEsperado(costeFinalResultado, 100);

        assertEquals(costeFinalResultado, 100);
    }
}
