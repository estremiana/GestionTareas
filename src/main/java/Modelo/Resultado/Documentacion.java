package Modelo.Resultado;

public class Documentacion extends Resultado {
    String formato;
    int paginas;
    double espacioDisco;

    public Documentacion() { }

    public Documentacion(String identificador, int horasInvertidas, boolean interno, String formato, int paginas, double espacioDisco) {
        super(identificador, horasInvertidas, interno);
        this.formato = formato;
        this.paginas = paginas;
        this.espacioDisco = espacioDisco;
    }

    @Override
    public String tipo() {return "Documentación";}
}
