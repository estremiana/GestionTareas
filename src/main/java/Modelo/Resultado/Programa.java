package Modelo.Resultado;

public class Programa extends Resultado {
    String lenguaje;
    int lineas;
    int modulos;

    public Programa() { }

    public Programa(String identificador, int horasInvertidas, boolean interno, String lenguaje, int lineas, int modulos) {
        super(identificador, horasInvertidas, interno);
        this.lenguaje = lenguaje;
        this.lineas = lineas;
        this.modulos = modulos;
    }

    @Override
    public String tipo() {return "Programa";}

}
