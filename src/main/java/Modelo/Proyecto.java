package Modelo;

import Modelo.Excepciones.PersonaNoPerteneceException;
import Modelo.Excepciones.PersonaYaPerteneceException;
import Modelo.Interfaces.CambioModeloProyecto;
import Modelo.Interfaces.InterrogaModelo;
import Modelo.Menu.MenuPrioridad;
import Modelo.Menu.MenuResultado;
import Modelo.Resultado.*;
import Modelo.Interfaces.tieneLista;
import Vista.InformaVista;

import java.io.*;
import java.util.*;
import java.util.List;

public class Proyecto implements tieneLista<Persona>, Serializable, CambioModeloProyecto, InterrogaModelo {
    List<Tarea> tareas;
    List<Persona> personas;
    String nombreProyecto;
    InformaVista vista;

    Identificador identificar = new Identificador();

    public Proyecto (String titulo) {
        this.nombreProyecto = titulo;
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }


    public Proyecto() {
        this.personas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public void darDeAltaTrabajador (String nombre, String correo) {
        Persona persona = new Persona(nombre, correo);
        try {
            anadirTrabajador(persona);
        } catch (PersonaYaPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void darDeAltaTarea (String titulo, String descripcion, int intPrioridad, MenuResultado tipo, String etiquetas) {
        MenuPrioridad prioridad = MenuPrioridad.getOpcion(intPrioridad);
        Resultado resultado = identificar.resultado(tipo);
        Tarea nuevaTarea = new Tarea(titulo, descripcion, prioridad, resultado, etiquetas);
        tareas.add(nuevaTarea);
    }

    public void asignarResponsableATarea (String tituloTarea, String correoResponsable) {
        Tarea tarea;
        Persona persona;
        try {
                tarea = identificar.tarea(tituloTarea, tareas);
                persona = identificar.persona(correoResponsable, personas);
                tarea.anadirResponsable(persona);
        } catch (IllegalArgumentException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public String listarTareas () {
        StringBuilder lista = new StringBuilder();
        lista.append("\n");
        if (tareas.isEmpty()) {
            lista.append("No hay tareas asignadas al proyecto ").append(nombreProyecto).append("\n");
        } else {
            lista.append("Las tareas del proyecto ").append(nombreProyecto).append(" son: \n");
            for (Tarea tarea : tareas) {
                lista.append(tarea.toString()).append("\n");
            }
        }
        return lista.toString();
    }

    public String listarPersonasAsignadasProyecto() {
        StringBuilder lista = new StringBuilder();
        if (personas.isEmpty()) {
            lista.append("No hay personas asignadas al proyecto ").append(nombreProyecto).append("\n");
        } else {
            lista.append("Las personas asignadas al proyecto ").append(nombreProyecto).append(" son:\n");
            for (Persona persona : personas) {
                lista.append(persona.toString()).append("\n");
            }
        }
        return lista.toString();
    }

    public void anadirPersonaATarea(String titulo, String correo) {
        try {
            Tarea tarea = identificar.tarea(titulo, tareas);
            Persona persona = identificar.persona(correo, personas);
            tarea.anadirPersona(persona);
        } catch (IllegalArgumentException | PersonaYaPerteneceException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersonaDeTarea(String titulo, String correo) {
        try {
            Tarea tarea = identificar.tarea(titulo, tareas);
            Persona persona = identificar.persona(correo, personas);
            tarea.eliminarPersona(persona);
        } catch (IllegalArgumentException | PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
    }

    public void marcarTareaComoFinalizada(String titulo) {
        try {
            identificar.tarea(titulo, tareas).finalizar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public List<Persona> listaPersonasNoResponsables(List<Persona> personas) {
        return UtilidadesParaListas.elementosConListaVacia(personas);
    }

    public List<Tarea> listaTareasSinPersonas() {
        return UtilidadesParaListas.elementosConListaVacia(tareas);
    }


    public void anadirTrabajador(Persona persona) throws PersonaYaPerteneceException {
        if (persona.getNombre().equals(""))
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        else if(persona.getClave().equals(""))
            throw new IllegalArgumentException("El correo no puede estar vacío");
        else if (UtilidadesParaListas.sePuedeInsertar(persona, this))
            this.personas.add(persona);
        else
            throw new PersonaYaPerteneceException(persona);
    }

    public void anadirCosteATarea(String tituloTarea, float coste) {
        try {
            identificar.tarea(tituloTarea, tareas).setCoste(coste);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //GETTERS

    public List<Persona> getTrabajadores() {
        return personas;
    }
    public List<Tarea> getTareas() {
        return tareas;
    }

    @Override
    public String[] getTiposResultado() {
        return MenuResultado.getTipos();
    }

    @Override
    public Tarea identificarTarea(String titulo) {
        return identificar.tarea(titulo, tareas);
    }

    @Override
    public String[] getTiposPrioridad() {
        return MenuPrioridad.getTipos();
    }

    @Override
    public List<Persona> getPersonas() {
        return personas;
    }

    @Override
    public String[] getTiposFacturacion() {
        return new String[]{"Consumo Interno", "Descuento", "Urgente"};
    }

    @Override
    public List<String> getTitulosTareas() {
        List<String> nombres = new ArrayList<>();
        for (Tarea tarea : tareas) {
            nombres.add(tarea.getTitulo());
        }
        return nombres;
    }

    @Override
    public Persona identificarPersona(String correo) {
        try {
            return identificar.persona(correo, personas);
        } catch (PersonaNoPerteneceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNombreProyecto() {return nombreProyecto;}


    @Override
    public List<Persona> getLista() {
        return personas;
    }

    @Override
    public void importarProyecto(String fichero) {
        Proyecto proyecto = Serializacion.cargarDatosDeFichero(fichero);
        if (proyecto == null)
            vista.importacionFallida();
        else {
            this.tareas = proyecto.tareas;
            this.nombreProyecto = proyecto.nombreProyecto;
            this.personas = proyecto.personas;
            vista.importacionCorrecta();
        }
    }

    @Override
    public void exportarProyecto(String fichero) {
        if (Serializacion.guardarDatosAFichero(this, fichero))
            vista.exportacionCorrecta();
        else
            vista.exportacionFallida();

    }

    public void setNombre(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;

    }

    @Override
    public void setNombreProyecto(String nombreProyecto) {
        if (nombreProyecto.equals(""))
            vista.errorNombreProyecto();
        else {
            setNombre(nombreProyecto);
            vista.importacionCorrecta();
        }
    }

    @Override
    public void darDeAltaTarea(String titulo, String descripcion, String prioridad, String resultado, String coste) {
        try {
            tareaApta(titulo);
            Tarea nuevaTarea = new Tarea(titulo, descripcion, identificar.prioridad(prioridad), identificar.resultado(resultado), new String() );
            tareas.add(nuevaTarea);
            nuevaTarea.setVista(vista);
            nuevaTarea.setCoste(Float.parseFloat(coste));
            vista.tareaAnyadida(titulo);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            vista.tareaNoAnyadida(e.getMessage());
        }
    }

    @Override
    public void nuevoTrabajador(String nombre, String correo) {
        try {
            Persona persona = new Persona(nombre, correo);
            persona.setVista(vista);
            anadirTrabajador(persona);
            vista.personaAnyadida(persona);
        } catch (PersonaYaPerteneceException | IllegalArgumentException e) {
            e.printStackTrace();
            vista.errorAlGuardar(e.getMessage());
        }
    }

    private void tareaApta(String tituloTarea)
    throws IllegalArgumentException{
        if (tituloTarea.equals("")) {
            throw new IllegalArgumentException("El titulo de la tarea no puede estar vacío");
        }
        for (Tarea tarea : tareas)
            if (tarea.getTitulo().equals(tituloTarea))
                throw new IllegalArgumentException("Ya hay una tarea con este nombre");
    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

    @Override
    public String nombreProyecto() {
        return nombreProyecto;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
