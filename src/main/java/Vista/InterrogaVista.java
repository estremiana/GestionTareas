package Vista;

import Modelo.Interfaces.CambioModeloPersona;
import Modelo.Interfaces.CambioModeloTarea;
import Modelo.Persona;

import java.util.List;
import java.util.Map;

public interface InterrogaVista {
    String getUbicacionFichero();

    String getNuevoNombreProyecto();

    String getNuevoDescripcion();

    List<Persona> getNuevoPersonasAsignadas();

    CambioModeloTarea getTareaActual();

    Persona getNuevoResponsable();

    String getNuevoPrioridad();

    boolean getNuevoFinalizado();

    String getNuevoResultado();

    String getNuevoListaEtiquetas();

    float getNuevoCoste();

    String getNuevoFacturacion();

    Map<String, String> getDatosNuevaTarea();

    boolean getNuevoDescuento();

    CambioModeloPersona getPersonaActual();

    Map<String, String> getDatosNuevaPersona();
}
