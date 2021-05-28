package Modelo.Facturacion;

import Modelo.Tarea;

import java.io.Serializable;

public interface Facturacion extends Serializable {
    float calcularCoste(Tarea tarea);
}
