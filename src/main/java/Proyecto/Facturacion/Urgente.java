package Proyecto.Facturacion;

import Proyecto.Tarea;

public class Urgente implements Facturacion{
    @Override
    public float calcularCoste(Tarea tarea) {
        float costeFinal = tarea.getCoste();
        switch (tarea.getPrioridad()) {
            case ALTA -> costeFinal *= 1.2;
            case MEDIA -> costeFinal *= 1.1;
        }
        return costeFinal;
    }
}
