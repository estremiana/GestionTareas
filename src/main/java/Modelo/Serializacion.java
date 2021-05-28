package Modelo;


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class Serializacion {
    public Serializacion() { }


    public static boolean guardarDatosAFichero(Proyecto proyecto, String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos);){
                oos.writeObject(proyecto);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Proyecto cargarDatosDeFichero(String nombreArchivo){
        try (FileInputStream fis = new FileInputStream(nombreArchivo)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)){
                return (Proyecto) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
