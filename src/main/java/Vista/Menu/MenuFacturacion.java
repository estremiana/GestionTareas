package Vista.Menu;

public enum MenuFacturacion {
    CONSUMO_INTERNO("Facturación de consumo interno"),
    DESCUENTO("Facturación con descuento por responsable de la tarea"),
    URGENTE("Facturación urgente (depende de la prioridad de la tarea)");

    private String descripcion;

    MenuFacturacion(String descripcion){
        this.descripcion = descripcion;
    }

    public static MenuFacturacion getOpcion (int posicion){
        return  values()[posicion];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuFacturacion opcion: MenuFacturacion.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }
}
