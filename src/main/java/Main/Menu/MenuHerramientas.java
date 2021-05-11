package Main.Menu;

public enum MenuHerramientas {
    DAR_DE_ALTA_TRABAJADOR("Dar de alta a un trabajador"),
    DAR_DE_ALTA_TAREA("Dar de alta una tarea"),
    LISTAR_TAREAS("Mostrar una lista con las tareas existentes"),
    LISTAR_PERSONAS_ASIGNADAS("Mostrar una lista con las personas asignadas a un proyecto"),
    ANADIR_PERSONA_A_TAREA("Añadir a una persona a una tarea"),
    ANADIR_RESPONSABLE_A_TAREA("Asignar una persona responsable a una tarea"),
    ELIMINAR_PERSONA_TAREA("Eliminar a una persona de una tarea"),
    MARCAR_TAREA_FINALIZADA("Dar una tarea por finalizada"),
    GUARDAR_DATOS_A_ARCHIVO("Guardar los datos en un archivo"),
    ANADIR_FACTURACION("Añadir tipo de facturación a una tarea"),
    ANADIR_COSTE("Añadir coste aproximado de un tarea"),
    SALIR("Atrás");

    private String descripcion;

    MenuHerramientas(String descripcion){
        this.descripcion = descripcion;
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
}
