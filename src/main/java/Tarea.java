import java.util.Date;
import java.util.List;

public class Tarea {
    String titulo;
    String descripcion;
    List<Persona> listaPersonasAsignadas;
    Persona responsable;
    int prioridad;
    Date fechaCreacion;
    Date fechaFinalizacion;
    boolean finalizado;
    Resultado resultado;
    List<String> listaEtiquetas;

}
