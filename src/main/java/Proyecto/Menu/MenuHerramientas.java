package Proyecto.Menu;

import Proyecto.Menu.Herramientas.*;
import Proyecto.Proyecto;

public enum MenuHerramientas {
    DAR_DE_ALTA_TRABAJADOR("Dar de alta a un trabajador", new DarDeAltaTrabajador()) ,
    DAR_DE_ALTA_TAREA("Dar de alta una tarea", new DarDeAltaTarea()),
    LISTAR_TAREAS("Mostrar una lista con las tareas existentes", new ListarTareas()),
    LISTAR_PERSONAS_ASIGNADAS("Mostrar una lista con las personas asignadas a una tarea", new ListarPersonasAsignadas()),
    ANADIR_PERSONA_A_TAREA("Añadir a una persona a una tarea", new AnadirPersonaATarea()),
    ELIMINAR_PERSONA_TAREA("Eliminar a una persona de una tarea", new EliminarPersonaTarea()),
    MARCAR_TAREA_FINALIZADA("Dar una tarea por finalizada", new MarcarTareaFinalizada()),
    SALIR("Salir", new Salir());

    private String descripcion;
    private Herramienta herramienta;


    MenuHerramientas(String descripcion, Herramienta herramienta){
        this.descripcion = descripcion;
        this.herramienta = herramienta;
    }

    public static MenuHerramientas getOpcion (int posicion){
        return  values()[posicion];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuHerramientas opcion: MenuHerramientas.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void accion(Proyecto proyecto) {
        herramienta.accion(proyecto);
    }
}
