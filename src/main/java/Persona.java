import java.util.ArrayList;
import java.util.List;

public class Persona {
    String nombre;
    String correoElectronico;
    List<Tarea> listaTareasResponsable;

    public Persona () {

    }

    public Persona (String  nombre, String correo){
        this.nombre = nombre;
        this.correoElectronico = correo;
        this.listaTareasResponsable = new ArrayList<>();
    }

    public void asignarTarea(Tarea nuevaTarea) {
        listaTareasResponsable.add(nuevaTarea);
    }

    @Override
    public String toString() {
        return " - " + nombre + ", correo : " + correoElectronico;
    }
}
