package Proyecto.Facturacion;

import Proyecto.Persona;
import Proyecto.Tarea;

import java.util.ArrayList;
import java.util.List;

public class Descuento implements Facturacion{
    List<Persona> listaPersonasConDescuento;
    public Descuento () {
        listaPersonasConDescuento = new ArrayList<>();
    }

    @Override
    public float calcularCoste(Tarea tarea) {
        float costeFinal = tarea.getCoste();
        Persona responsable = tarea.getResponsable();
        for (Persona persona : listaPersonasConDescuento)
            if (persona.equals(responsable))
                return (float) (costeFinal * 0.8);
        return costeFinal;
    }

    public void anadirPersonasConDescuento(Persona persona) {
        listaPersonasConDescuento.add(persona);
    }
}
