package Vista.Menu;

public enum MenuInicio {
    CARGAR_PROYECTO("Cargar un proyecto desde un archivo"),
    NUEVO_PROYECTO("Crear un nuevo proyecto desde cero"),
    SALIR("Salir");

    private String descripcion;

    MenuInicio(String descripcion){
        this.descripcion = descripcion;
    }

    public static MenuInicio getOpcion (int posicion){
        return  values()[posicion];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuInicio opcion: MenuInicio.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }
}
