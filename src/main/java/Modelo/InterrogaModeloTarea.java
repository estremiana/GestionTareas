package Modelo;

import Modelo.Facturacion.Facturacion;
import Modelo.Menu.MenuPrioridad;
import Modelo.Resultado.Resultado;

import java.util.Date;
import java.util.List;

public interface InterrogaModeloTarea {
    String getTitulo();

    String getDescripcion();

    Object getFechaCreacion();

    MenuPrioridad getPrioridad();

    Persona getResponsable();

    Date getFechaFinalizacion();

    boolean isFinalizado();

    Resultado getResultado();

    float getCoste();

    Facturacion getFacturacion();

    List<Persona> getPersonasAsignadas();

    float getCosteFinal();
}
