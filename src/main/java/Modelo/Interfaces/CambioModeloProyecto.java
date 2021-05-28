package Modelo.Interfaces;

public interface CambioModeloProyecto {
    void importarProyecto(String fichero);
    void setNombreProyecto(String nombreProyecto);


    void darDeAltaTarea(String titulo, String descripcion, String prioridad, String resultado, String coste);

    void nuevoTrabajador(String nombre, String correo);

    void exportarProyecto(String fichero);
}
