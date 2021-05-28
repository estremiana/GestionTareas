package Modelo.Facturacion;

import Modelo.Tarea;

public class Descuento implements Facturacion {

    @Override
    public float calcularCoste(Tarea tarea) {
        float costeFinal = tarea.getCoste();
        if (tarea.getResponsable()!= null && tarea.getResponsable().tieneDescuento())
            costeFinal *= 0.8;
        return costeFinal;
    }

    @Override
    public String toString() {
        return "Descuento";
    }
}
