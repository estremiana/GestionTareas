package Modelo.Menu;

import java.util.ArrayList;
import java.util.List;

public enum MenuPrioridad {
    ALTA("Prioridad alta", "Alta"),
    MEDIA("Prioridad media", "Media"),
    BAJA("Prioridad baja", "Baja");

    private final String descripcion;
    private final String tipo;

    MenuPrioridad(String descripcion, String tipo){
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public static MenuPrioridad getOpcion (int posicion){
        return  values()[posicion];
    }

    public static String getTipo (MenuPrioridad opcion) {
        if (opcion != null)
            return opcion.tipo;
        else
            return "";
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

    public static String[] getTipos() {
        List<String> tipos = new ArrayList<>();
        for(MenuPrioridad opcion: MenuPrioridad.values()) {
            tipos.add(opcion.tipo);
        }
        return tipos.toArray(new String[]{});
    }


}
