package Modelo.Menu;

public enum MenuResultado {
    BIBLIOTECA("Resultado de la tarea : Biblioteca"),
    PAGINAWEB("Resultado de la tarea : Página web"),
    PROGRAMA("Resultado de la tarea : Programa"),
    DOCUMENTACION("Resultado de la tarea : Documentación");

    private String descripcion;

    MenuResultado(String descripcion){
        this.descripcion = descripcion;
    }

    public static MenuResultado getOpcion (int posicion){
        return  values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuResultado opcion: MenuResultado.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }
}
