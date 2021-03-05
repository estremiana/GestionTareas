import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Proyecto prueba = new Proyecto("Prueba");
        prueba.darDeAltaTrabajador("Daniel Estremiana", "daniestremiana@gmail.com");
        prueba.darDeAltaTrabajador("Óscar Monedero", "oscarmonedero@gmail.com");
        List<String> correos = new ArrayList<>();
        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("hola");
        correos.add("oscarmonedero@gmail.com");

        prueba.darDeAltaTarea("Acabar esto", "pues hay que programar cositas", "daniestremiana@gmail.com", correos, "Alta", "Documentación", etiquetas);
        System.out.println(prueba.listarPersonasAsignadasProyecto());
        prueba.marcarTareaComoFinalizada("Acabar esto");
        System.out.println(prueba.listarTareas());
    }
}
