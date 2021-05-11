package Modelo.Resultado;

public class Biblioteca extends Resultado {
    String nombre;
    int libros;
    int socios;

    public Biblioteca() { }

    public Biblioteca(String identificador, int horasInvertidas, boolean interno, String nombre, int libros, int socios) {
        super(identificador, horasInvertidas, interno);
        this.nombre = nombre;
        this.libros = libros;
        this.socios = socios;
    }

    @Override
    public String tipo() {return "Biblioteca";}
}
