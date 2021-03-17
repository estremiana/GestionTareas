public enum Menu{
    BIBLIOTECA("Resultado de la tarea : Biblioteca"),
    PAGINAWEB("Resultado de la tarea : Página web"),
    PROGRAMA("Resultado de la tarea : Programa"),
    DOCUMENTACION("Resultado de la tarea : Documentación");

    private String descripcion;

    Menu(String descripcion){
        this.descripcion = descripcion;
    }

    public static Menu getOpcion (int posicion){
        return  values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(Menu opcion: Menu.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
        }
        return sb.toString();
    }
}
