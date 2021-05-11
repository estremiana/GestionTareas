package Modelo.Excepciones;

import Modelo.Persona;

public class PersonaNoPerteneceException extends Exception{
    public PersonaNoPerteneceException(Persona persona) {
        super("La persona" + persona + " no pertenece al proyecto/tarea");
    }

    public PersonaNoPerteneceException(String correo) {
        super("La persona con correo " + correo + " no se ha encontrado");
    }
}
