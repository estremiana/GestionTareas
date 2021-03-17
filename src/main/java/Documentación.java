public class Documentación extends Resultado{
    String formato;
    int paginas;
    double espacioDisco;

    public Documentación() { }

    public Documentación(String identificador, int horasInvertidas, boolean interno, String formato, int paginas, double espacioDisco) {
        super(identificador, horasInvertidas, interno);
        this.formato = formato;
        this.paginas = paginas;
        this.espacioDisco = espacioDisco;
    }

    String tipo() {

        return "Documentación";}


}
