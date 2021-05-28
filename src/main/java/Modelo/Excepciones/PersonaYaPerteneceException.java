package Modelo.Excepciones;

import Modelo.Persona;

public class PersonaYaPerteneceException extends Exception{
    public PersonaYaPerteneceException(Persona persona) {
        super("El correo de la persona" + persona + " ya pertenece al proyecto/tarea");
    }
}
