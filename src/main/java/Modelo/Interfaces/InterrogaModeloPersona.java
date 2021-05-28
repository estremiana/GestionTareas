package Modelo.Interfaces;

import java.util.List;

public interface InterrogaModeloPersona {
    boolean tieneDescuento();
    List<String> getTareasDeLasQueEsResponsable();

    String getClave();

    String getNombre();

    CambioModeloPersona getPersona();
}
