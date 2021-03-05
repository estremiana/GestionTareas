import java.util.List;

public class Persona {
    String nombre;
    String correoElectronico;
    List<Tarea> listaTareasResponsable;

    public Persona (String  nombre, String correo){
        this.nombre = nombre;
        this.correoElectronico = correo;
    }
}
