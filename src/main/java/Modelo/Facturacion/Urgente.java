package Modelo.Facturacion;

import Modelo.Tarea;

public class Urgente implements Facturacion {
    @Override
    public float calcularCoste(Tarea tarea) {
        float costeFinal = tarea.getCoste();
        if (tarea.getPrioridad()!= null) {
            switch (tarea.getPrioridad()) {
                case ALTA -> costeFinal *= 1.3;
                case MEDIA -> costeFinal *= 1.2;
                case BAJA -> costeFinal *= 1.1;
            }
        }
        return costeFinal;
    }

    @Override
    public String toString() {
        return "Urgente";
    }
}
