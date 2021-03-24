package Resultado;

public class PaginaWeb extends Resultado {
    String tipo;
    String lenguaje;
    String backEnd;

    public PaginaWeb() { }

    public PaginaWeb(String identificador, int horasInvertidas, boolean interno, String tipo, String lenguaje, String backEnd) {
        super(identificador, horasInvertidas, interno);
        this.tipo = tipo;
        this.lenguaje = lenguaje;
        this.backEnd = backEnd;
    }

    @Override
    public String tipo() {return "Página Web";}
}
