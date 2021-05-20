package Modelo;

import Modelo.Excepciones.PersonaNoPerteneceException;
import Modelo.Excepciones.PersonaYaPerteneceException;
import Modelo.Facturacion.Facturacion;
import Modelo.Menu.MenuPrioridad;
import Modelo.Resultado.Resultado;
import Modelo.Interfaces.*;

import java.io.Serializable;
import java.util.*;

public class Tarea implements tieneLista<Persona>, tieneClave<String>, Serializable, InterrogaModeloTarea {
    String titulo;
    String descripcion;
    List<Persona> listaPersonasAsignadas;
    Persona responsable;
    MenuPrioridad prioridad;
    Date fechaCreacion;
    Date fechaFinalizacion;
    boolean finalizado;
    Resultado resultado;
    List<String> listaEtiquetas;
    float coste;
    Facturacion facturacion;

    public Tarea(){
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
        this.responsable = null;
        this.prioridad = prioridad;
        this.fechaCreacion = new Date();
        this.finalizado = false;
        this.resultado = resultado;
        this.listaEtiquetas = listaEtiquetas;
        this.coste = 0;
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
        if (listaPersonasAsignadas.contains(persona)) {
            responsable = persona;
            responsable.asignarTarea(this);
        }
        else
            throw new PersonaNoPerteneceException(persona);
    }

    public void eliminarPersona(Persona persona) throws PersonaNoPerteneceException {
        if (UtilidadesParaListas.sePuedeInsertar(persona, this))    //Si se puede insertar es que no está
            throw new PersonaNoPerteneceException(persona);
        else if (responsable.equals(persona))
            eliminarResponsable();
        listaPersonasAsignadas.remove(persona);
    }

    public void eliminarResponsable() {
        responsable.eliminarTarea(this);
        responsable = null;
    }


    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        cadena.append(" - ").append(titulo).append(".\n");
        cadena.append("\tPersonas asignadas: \n");
        for (Persona persona : listaPersonasAsignadas)
            cadena.append("\t").append(persona).append("\n");
        cadena.append("\tSiendo el responsable:\n");
        if (responsable != null)
            cadena.append("\t").append(responsable).append("\n");
        cadena.append("\tEl coste aproximado de la tarea es: ").append(coste).append("\n");
        if (facturacion != null)
            cadena.append("\tEl coste teniendo en cuenta la facturación: ").append(facturacion.calcularCoste(this)).append("\n");
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

    public Persona getResponsable() {
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

    public float getCoste() {
        return coste;
    }

    //SETTERS

    public void setResponsable (Persona persona) {
        responsable = persona;
    }

    public void setCoste (float coste) { this.coste = coste;}

    public void setPrioridad(MenuPrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    @Override
    public String getClave() {
        return titulo;
    }

    @Override
    public List<Persona> getLista() {
        return listaPersonasAsignadas;
    }

    public Facturacion getFacturacion() {
        return facturacion;
    }

    @Override
    public List<Persona> getPersonasAsignadas() {
        return listaPersonasAsignadas;
    }

    @Override
    public float getCosteFinal() {
        if (facturacion != null)
            return facturacion.calcularCoste(this);
        else
            return this.coste;
    }
}