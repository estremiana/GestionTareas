package Modelo;

import Modelo.Excepciones.PersonaNoPerteneceException;
import Modelo.Excepciones.PersonaYaPerteneceException;
import Modelo.Facturacion.ConsumoInterno;
import Modelo.Facturacion.Descuento;
import Modelo.Facturacion.Facturacion;
import Modelo.Facturacion.Urgente;
import Modelo.Menu.MenuPrioridad;
import Modelo.Resultado.*;
import Modelo.Interfaces.*;
import Vista.InformaVista;

import java.io.Serializable;
import java.util.*;

public class Tarea implements tieneLista<Persona>, tieneClave<String>, Serializable, InterrogaModeloTarea, CambioModeloTarea {
    String titulo;
    String descripcion;
    List<Persona> listaPersonasAsignadas;
    Persona responsable;
    MenuPrioridad prioridad;
    Date fechaCreacion;
    Date fechaFinalizacion;
    boolean finalizado;
    Resultado resultado;
    String etiquetas;
    float coste;
    Facturacion facturacion;
    InformaVista vista;
    Identificador identificar = new Identificador();

    public Tarea() {
        this.fechaCreacion = new Date();
        this.finalizado = false;
        this.coste = 0;
        this.listaPersonasAsignadas = new ArrayList<>();
    }

    public Tarea(String titulo) {
        this.titulo = titulo;
        this.coste = 0;
        this.fechaCreacion = new Date();
        this.listaPersonasAsignadas = new ArrayList<>();
        this.finalizado = false;
    }

    public Tarea(String titulo, String descripcion, MenuPrioridad prioridad, Resultado resultado, String etiquetas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.listaPersonasAsignadas = new ArrayList<>();
        this.responsable = null;
        this.prioridad = prioridad;
        this.fechaCreacion = new Date();
        this.finalizado = false;
        this.resultado = resultado;
        this.etiquetas = etiquetas;
        this.coste = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return finalizado == tarea.finalizado && Objects.equals(titulo, tarea.titulo) && Objects.equals(descripcion, tarea.descripcion) && Objects.equals(listaPersonasAsignadas, tarea.listaPersonasAsignadas) && Objects.equals(responsable, tarea.responsable) && prioridad == tarea.prioridad && Objects.equals(resultado.getClass(), tarea.resultado.getClass()) && Objects.equals(etiquetas, tarea.etiquetas);
    }

    public void finalizar() {
        finalizado = true;
        fechaFinalizacion = new Date();
    }

    public void noFinalizar() {
        finalizado = false;
        fechaFinalizacion = null;
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
        } else
            throw new PersonaNoPerteneceException(persona);
    }

    public void eliminarPersona(Persona persona) throws PersonaNoPerteneceException {
        if (UtilidadesParaListas.sePuedeInsertar(persona, this))    //Si se puede insertar es que no está
            throw new PersonaNoPerteneceException(persona);
        else if (responsable.equals(persona))
            eliminarResponsable();
        listaPersonasAsignadas.remove(persona);
        persona.eliminarTarea(this);
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

    public String getEtiquetas() {
        return etiquetas;
    }

    public float getCoste() {
        return coste;
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
    public float calcularCosteFinal() {
        if (facturacion != null)
            return facturacion.calcularCoste(this);
        else
            return this.coste;
    }

    @Override
    public CambioModeloTarea getTarea() {
        return this;
    }


    //SETTERS

    @Override
    public void setResponsable(Persona persona) {
        if (persona == null) {
            if (responsable != null)
                eliminarResponsable();
        } else {
                try {
                    anadirResponsable(persona);
                } catch (PersonaNoPerteneceException e) {
                    e.printStackTrace();
                    vista.errorAlGuardar(e.getMessage());
                }
            }
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    @Override
    public void setNuevaPrioridad(String prioridad) {
        this.prioridad = identificar.prioridad(prioridad);
    }

    public void setPrioridad(MenuPrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
    }

    public void setNuevoFacturacion(String facturacion) {
        this.facturacion = switch (facturacion) {
            case "Consumo Interno" -> new ConsumoInterno();
            case "Descuento" -> new Descuento();
            case "Urgente" -> new Urgente();
            default -> null;
        };
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPersonasAsignadas(List<Persona> listaPersonasAsignadas) {
        this.listaPersonasAsignadas = listaPersonasAsignadas;
        if (!listaPersonasAsignadas.contains(responsable) && responsable != null)
            eliminarResponsable();
    }

    public void setFinalizado(boolean finalizado) {
        if (finalizado)
            finalizar();
        else
            noFinalizar();
    }

    @Override
    public void setResultado(String resultado) {
        this.resultado = identificar.resultado(resultado);
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public void setEtiquetas(String etiquetas) { this.etiquetas = etiquetas; }

    @Override
    public void datosActualizados() {
        vista.guardadoCorrectoTareas();
    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }
}

