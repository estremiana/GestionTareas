public abstract class Resultado {
    String identificador;
    int horasInvertidas;
    boolean interno;

    public Resultado() {

    }

    public Resultado(String identificador, int horasInvertidas, boolean interno) {
        this.identificador = identificador;
        this.horasInvertidas = horasInvertidas;
        this.interno = interno;
    }

    abstract String tipo();
}
