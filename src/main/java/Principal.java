import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Proyecto prueba = new Proyecto("Prueba");
        prueba.darDeAltaTrabajador("Daniel Estremiana", "daniestremiana@gmail.com");
        prueba.darDeAltaTrabajador("Óscar Monedero", "oscarmonedero@gmail.com");
        prueba.darDeAltaTrabajador("Pablo Pitarch", "ppitarch@gmail.com");
        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("hola");

        prueba.darDeAltaTarea();
        prueba.añadirPersonaATarea("ppitarch@gmail.com", "Acabar esto");
        System.out.println(prueba.listarPersonasAsignadasProyecto());
        prueba.marcarTareaComoFinalizada("Acabar esto");
        System.out.println(prueba.listarTareas());
    }


}
