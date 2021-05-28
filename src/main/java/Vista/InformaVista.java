package Vista;

import Modelo.Persona;

public interface InformaVista {
    void importacionCorrecta();
    void importacionFallida();

    void tareaAnyadida(String titulo);

    void guardadoCorrectoTareas();

    void tareaNoAnyadida(String mensajeError);

    void errorAlGuardar(String message);

    void guardadoCorrectoPersonas();

    void personaAnyadida(Persona persona);

    void exportacionCorrecta();

    void exportacionFallida();

    void errorNombreProyecto();
}
