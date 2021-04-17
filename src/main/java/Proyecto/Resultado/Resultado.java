package Proyecto.Resultado;

import Main.Serializacion;

import java.io.Serializable;

public abstract class Resultado implements Serializable {
    String identificador;
    int horasInvertidas;
    boolean interno;

    public Resultado() { }

    public Resultado(String identificador, int horasInvertidas, boolean interno) {
        this.identificador = identificador;
        this.horasInvertidas = horasInvertidas;
        this.interno = interno;
    }

    public abstract String tipo();
}
