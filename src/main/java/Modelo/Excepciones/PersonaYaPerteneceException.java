package Modelo.Excepciones;

import Modelo.Persona;

public class PersonaYaPerteneceException extends Exception{
    public PersonaYaPerteneceException(Persona persona) {
        super("La persona" + persona + " ya pertenece al proyecto/tarea");
    }
}
