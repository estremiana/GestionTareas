package Main;

import Proyecto.Proyecto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;



public class Serializacion {
    public Serializacion() { }

    private final GestionES entrada = new GestionES();

    public void guardarDatosAFichero(Proyecto proyecto) {
        String nombreArchivo = entrada.nombreArchivo();
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos);){
                oos.writeObject(proyecto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Proyecto cargarDatosDeFichero(Proyecto proyecto){
        String nombreArchivo = entrada.nombreArchivo();
        try (FileInputStream fis = new FileInputStream(nombreArchivo)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)){
                proyecto = (Proyecto)ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return proyecto;
    }
}
