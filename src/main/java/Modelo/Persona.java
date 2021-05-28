package Modelo;

import Modelo.Interfaces.CambioModeloPersona;
import Modelo.Interfaces.InterrogaModeloPersona;
import Modelo.Interfaces.tieneClave;
import Modelo.Interfaces.tieneLista;
import Vista.InformaVista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Persona implements tieneClave<String>, tieneLista<Tarea>, Serializable, InterrogaModeloPersona, CambioModeloPersona {
    String nombre;
    String correoElectronico;
    boolean descuento;
    List<Tarea> listaTareasResponsable;
    InformaVista vista;

    public Persona() { }

    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correoElectronico = correo;
        this.descuento = false;
        this.listaTareasResponsable = new ArrayList<>();
    }

    public void asignarTarea(Tarea nuevaTarea) {
        if (!listaTareasResponsable.contains(nuevaTarea))
            listaTareasResponsable.add(nuevaTarea);
    }

    public void eliminarTarea(Tarea tareaVieja) {
        listaTareasResponsable.remove(tareaVieja);
    }

    @Override
    public String toString() {
        return nombre + ", correo : " + correoElectronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(correoElectronico, persona.correoElectronico);
    }

    @Override
    public String getClave() {
        return correoElectronico;
    }

    @Override
    public List<Tarea> getLista() {
        return listaTareasResponsable;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public CambioModeloPersona getPersona() {
        return this;
    }

    public boolean tieneDescuento() {
        return descuento;
    }

    @Override
    public List<String> getTareasDeLasQueEsResponsable() {

        List<String> tituloTareas = new ArrayList<>();
        for (Tarea tarea: listaTareasResponsable)
            tituloTareas.add(tarea.titulo);
        return tituloTareas;
    }
    @Override
    public void setDescuento(boolean descuento){ this.descuento = descuento; }

    @Override
    public void datosActualizados() {
        vista.guardadoCorrectoPersonas();
    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }
}

