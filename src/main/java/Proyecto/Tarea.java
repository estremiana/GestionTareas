package Proyecto;

import Proyecto.Excepciones.PersonaNoPerteneceException;
import Proyecto.Excepciones.PersonaYaPerteneceException;
import Proyecto.Menu.MenuPrioridad;
import Proyecto.Resultado.Resultado;
import Proyecto.Interfaces.*;

import java.io.Serializable;
import java.util.*;

public class Tarea implements tieneLista<Persona>, tieneClave<String>, Serializable {
    String titulo;
    String descripcion;
    List<Persona> listaPersonasAsignadas;
    Optional<Persona> responsable;
    MenuPrioridad prioridad;
    Date fechaCreacion;
    Date fechaFinalizacion;
    boolean finalizado;
    Resultado resultado;
    List<String> listaEtiquetas;

    public Tarea(){
        responsable = Optional.empty();
        this.fechaCreacion = new Date();
        this.finalizado = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return finalizado == tarea.finalizado && Objects.equals(titulo, tarea.titulo) && Objects.equals(descripcion, tarea.descripcion) && Objects.equals(listaPersonasAsignadas, tarea.listaPersonasAsignadas) && Objects.equals(responsable, tarea.responsable) && prioridad == tarea.prioridad && Objects.equals(resultado.getClass(), tarea.resultado.getClass()) && Objects.equals(listaEtiquetas, tarea.listaEtiquetas);
    }

    public Tarea(String titulo, String descripcion, MenuPrioridad prioridad, Resultado resultado, List<String> listaEtiquetas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.listaPersonasAsignadas = new ArrayList<>();
        this.responsable = Optional.empty();
        this.prioridad = prioridad;
        this.fechaCreacion = new Date();
        this.finalizado = false;
        this.resultado = resultado;
        this.listaEtiquetas = listaEtiquetas;
    }

    public void finalizar() {
        finalizado = true;
        fechaFinalizacion = new Date();
    }

    public void anadirPersona(Persona persona)
            throws PersonaYaPerteneceException {
        if (UtilidadesParaListas.sePuedeInsertar(persona, this))
            listaPersonasAsignadas.add(persona);
        else throw new PersonaYaPerteneceException(persona);
    }

    public void anadirResponsable(Persona persona)
    throws PersonaNoPerteneceException {
        if (listaPersonasAsignadas.contains(persona))
            responsable = Optional.ofNullable(persona);
        else
            throw new PersonaNoPerteneceException(persona);
    }

    public void eliminarPersona(Persona persona) throws PersonaNoPerteneceException {
        if (UtilidadesParaListas.sePuedeInsertar(persona, this))    //Si se puede insertar es que no está
            throw new PersonaNoPerteneceException(persona);
        else if (responsable.equals(Optional.ofNullable(persona)))
            responsable = Optional.empty();
        listaPersonasAsignadas.remove(persona);
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        cadena.append(" - ").append(titulo).append(".\n");
        cadena.append("\tPersonas asignadas: \n");
        for (Persona persona : listaPersonasAsignadas)
            cadena.append("\t").append(persona).append("\n");
        cadena.append("\tSiendo el responsable:\n");
        responsable.ifPresent(x -> cadena.append("\t").append(x).append("\n"));
        if (finalizado)
            cadena.append("\tLa tarea está finalizada\n");
        else
            cadena.append("\tLa tarea no está finalizada\n");
        cadena.append("\tResultado: ").append(resultado.tipo()).append("\n");
        return cadena.toString();
    }

    //GETTERS


    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Persona> getListaPersonasAsignadas() {
        return listaPersonasAsignadas;
    }

    public Optional<Persona> getResponsable() {
        return responsable;
    }

    public MenuPrioridad getPrioridad() {
        return prioridad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public List<String> getListaEtiquetas() {
        return listaEtiquetas;
    }

    //SETTERS

    public void setResponsable (Persona persona) {
        responsable = Optional.ofNullable(persona);
    }

    @Override
    public String getClave() {
        return titulo;
    }

    @Override
    public List<Persona> getLista() {
        return listaPersonasAsignadas;
    }


}