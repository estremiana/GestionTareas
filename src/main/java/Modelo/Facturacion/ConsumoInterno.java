package Modelo.Facturacion;

import Modelo.Tarea;

public class ConsumoInterno implements Facturacion{
    @Override
    public float calcularCoste(Tarea tarea) {
        return tarea.getCoste();
    }
}
