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

    public Tarea(){
        this.fechaCreacion = new Date();
        this.finalizado = false;
    }



    public Tarea(String titulo, String descripcion, List<Persona> listaPersonasAsignadas, Persona responsable, int prioridad, Resultado resultado, List<String> listaEtiquetas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.listaPersonasAsignadas = listaPersonasAsignadas;
        this.responsable = responsable;
        this.prioridad = prioridad;
        this.fechaCreacion = new Date();
        this.finalizado = false;
        this.resultado = resultado;
        this.listaEtiquetas = listaEtiquetas;
        if (!listaPersonasAsignadas.contains(responsable))
            this.listaPersonasAsignadas.add(responsable);
    }

    public void finalizar() {
        finalizado = true;
        fechaFinalizacion = new Date();
    }

    public void añadirPersona(Persona persona) {
        if (!listaPersonasAsignadas.contains(persona))
            listaPersonasAsignadas.add(persona);
    }

    public void eliminarPersona(Persona persona) {
        listaPersonasAsignadas.remove(persona);
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        cadena.append(" - ").append(titulo).append(".\n");
        cadena.append("\tPersonas asignadas: \n");
        for (Persona persona : listaPersonasAsignadas)
            cadena.append("\t").append(persona).append("\n");
        cadena.append("\tSiendo el responsable:\n");
        cadena.append("\t").append(responsable).append("\n");
        if (finalizado)
            cadena.append("\tLa tarea está finalizada\n");
        else
            cadena.append("\tLa tarea no está finalizada\n");
        cadena.append("\tResultado: ").append(resultado.tipo()).append("\n");
        return cadena.toString();
    }

}