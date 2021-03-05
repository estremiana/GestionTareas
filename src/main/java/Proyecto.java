import java.util.List;

public class Proyecto {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;

    public Proyecto (String nombre) {
        this.nombreProyecto = nombre;
    }

    public void darDeAltaTrabajador (String nombre, String correo) {
        this.personas.add(new Persona(nombre, correo));
    }

    public void darDeAltaTarea () {

    }
}
