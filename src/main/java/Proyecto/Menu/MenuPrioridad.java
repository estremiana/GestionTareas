package Proyecto.Menu;

public enum MenuPrioridad {
    ALTA("Prioridad alta"),
    MEDIA("Prioridad media"),
    BAJA("Prioridad baja");

    private String descripcion;

    MenuPrioridad(String descripcion){
        this.descripcion = descripcion;
    }

    public static MenuPrioridad getOpcion (int posicion){
        return  values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuPrioridad opcion: MenuPrioridad.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }
}
