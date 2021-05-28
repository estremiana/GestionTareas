package Modelo.Menu;

import java.util.ArrayList;
import java.util.List;

public enum MenuResultado {
    BIBLIOTECA("Resultado de la tarea : Biblioteca", "Biblioteca"),
    PAGINAWEB("Resultado de la tarea : Pagina web", "Pagina Web"),
    PROGRAMA("Resultado de la tarea : Programa", "Programa"),
    DOCUMENTACION("Resultado de la tarea : Documentacion", "Documentacion");

    private final String descripcion;
    private final String tipo;

    MenuResultado(String descripcion, String tipo){
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public static String[] getTipos() {
        List<String> tipos = new ArrayList<>();
        for(MenuResultado opcion: MenuResultado.values()) {
            tipos.add(opcion.tipo);
        }
        return tipos.toArray(new String[]{});
    }
}
