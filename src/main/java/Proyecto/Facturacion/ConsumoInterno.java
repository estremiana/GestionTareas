package Proyecto.Facturacion;

import Proyecto.Tarea;

public class ConsumoInterno implements Facturacion{
    @Override
    public float calcularCoste(Tarea tarea) {
        return tarea.getCoste();
    }
}
