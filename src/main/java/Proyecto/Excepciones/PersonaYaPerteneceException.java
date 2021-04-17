package Proyecto.Excepciones;

import Proyecto.Persona;

public class PersonaYaPerteneceException extends Exception{
    public PersonaYaPerteneceException(Persona persona) {
        super("La persona" + persona + " ya pertenece al proyecto/tarea");
    }
}
