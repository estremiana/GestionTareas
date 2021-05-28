package Modelo.Interfaces;

import Modelo.Persona;

import java.util.List;

public interface CambioModeloTarea {
    void setPersonasAsignadas(List<Persona> listaPersonasAsignadas);
    void setDescripcion(String descripcion);
    void setResponsable(Persona persona);
    void setCoste(float coste);
    void setNuevaPrioridad(String prioridad);
    void setNuevoFacturacion(String facturacion);
    void setFinalizado(boolean finalizado);
    void setResultado(String resultado);
    void setEtiquetas(String etiquetas);

    void datosActualizados();
}
