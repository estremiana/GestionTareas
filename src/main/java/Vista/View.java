package Vista;

import Controlador.ControladorInicio;
import Modelo.*;
import Modelo.Interfaces.*;
import Modelo.Menu.MenuPrioridad;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SpringLayout;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class View implements InterrogaVista, InformaVista, Serializable {
    private ControladorInicio controlador;
    private InterrogaModelo modelo;
    private InterrogaModeloTarea modeloTarea;
    private InterrogaModeloPersona modeloPersona;
    private ModeloTablaTareas modeloTablaTareas;
    private ModeloTablaPersonas modeloTablaPersonas;
    private ListSelectionModel lsmTablaTareas;
    private ListSelectionModel lsmTablaPersonas;
    private JFrame ventana;
    private Map<String, Accessible> parametrosTareas;
    private Map<String, Accessible> parametrosPersonas;
    private DefaultListModel<String> listaTareasEnPersonas = new DefaultListModel<>();
    private DefaultListModel<Persona> listaPersonasEnTareas = new DefaultListModel<>();


    public void creaInicio() {
        parametrosTareas = new HashMap<>();
        parametrosPersonas = new HashMap<>();
        SwingUtilities.invokeLater(this::inicio);
    }

    private void inicio() {
        ventana = new JFrame("Proyecto");

        JButton botonImportar = new JButton("Importar Proyecto");
        botonImportar.addActionListener(new EscuchadorPrincipal());
        JButton botonNuevo = new JButton("Nuevo Proyecto");
        botonNuevo.addActionListener(new EscuchadorPrincipal());

        JPanel fondo = new JPanel();
        fondo.setBorder(new EmptyBorder(10, 10, 10, 10));
        fondo.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        fondo.add(new JLabel("<html><h1><strong><i>Gestión Tareas</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());

        panelBotones.add(botonImportar, gbc);
        panelBotones.add(botonNuevo, gbc);

        gbc.weighty = 1;
        fondo.add(panelBotones, gbc);
        ventana.getContentPane().add(fondo);
        ventana.pack();
        ventana.setSize(450, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    private void paginaPrincipal() {
        ventana.setVisible(false);
        ventana = new JFrame(modelo.nombreProyecto());

        JPanel fondo = new JPanel();
        JTabbedPane pestanyas = new JTabbedPane();


        JPanel pestanyaTareas = new PestanyaTareas();
        JPanel pestanyaPersonas = new PestanyaPersonas();

        JButton botonExportar = new JButton("Exportar proyecto");
        JPanel panelExportar = new JPanel(new FlowLayout());
        panelExportar.add(botonExportar);

        botonExportar.addActionListener(new EscuchadorPrincipal());

        fondo.setLayout(new BorderLayout());

        pestanyas.addTab("Tareas", pestanyaTareas);
        pestanyas.addTab("Personas", pestanyaPersonas);

        fondo.add(pestanyas, BorderLayout.CENTER);
        fondo.add(panelExportar, BorderLayout.SOUTH);





        ventana.getContentPane().add(fondo);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setMinimumSize(fondo.getSize());
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private class PestanyaTareas extends JPanel {
        public PestanyaTareas() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new BorderLayout());


            modeloTablaTareas = new ModeloTablaTareas(modelo.getTareas());

            JTable tablaTareas = new JTable(modeloTablaTareas);

            JPanel contenedorTareas = new JPanel();
            contenedorTareas.setLayout(new BoxLayout(contenedorTareas, BoxLayout.Y_AXIS));
            JButton botonAddTarea = new JButton("Añadir tarea");
            botonAddTarea.addActionListener(new EscuchadorPrincipal());
            botonAddTarea.setAlignmentX(Component.LEFT_ALIGNMENT);
            contenedorTareas.add(botonAddTarea);
            tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lsmTablaTareas = tablaTareas.getSelectionModel();
            lsmTablaTareas.addListSelectionListener(new EscuchadorTablaTareas());
            contenedorTareas.add(new JScrollPane(tablaTareas));

            JPanel descripcionTareasContenedor = new JPanel();
            descripcionTareasContenedor.setLayout(new BoxLayout(descripcionTareasContenedor, BoxLayout.Y_AXIS));

            JPanel descripcionTareasDatos = new JPanel(new SpringLayoutSerializable());

            setUpDescripcionTareasDatos(descripcionTareasDatos);

            JButton guardar = new JButton("Guardar tarea");
            guardar.addActionListener(new EscuchadorPrincipal());


            descripcionTareasContenedor.add(descripcionTareasDatos);
            descripcionTareasContenedor.add(guardar);

            descripcionTareasContenedor.setBorder(new EmptyBorder(0, 10, 0, 0));

            layout.SpringUtilities.makeCompactGrid(descripcionTareasDatos,
                    13, 2,
                    0, 0,
                    5, 5);

            add(contenedorTareas, BorderLayout.CENTER);

            add(descripcionTareasContenedor, BorderLayout.EAST);
        }

        private void setUpDescripcionTareasDatos(JPanel descripcionTareasDatos) {
            JTextField tituloField = new JTextField(20);
            JLabel tituloLabel = new JLabel("Título: ");
            tituloField.setEnabled(false);
            descripcionTareasDatos.add(tituloLabel);
            descripcionTareasDatos.add(tituloField);
            parametrosTareas.put("Título", tituloField);

            JTextArea descripcionField = new JTextArea(3, 20);
            descripcionField.setLineWrap(true);
            descripcionField.setWrapStyleWord(true);
            JLabel descripcionLabel = new JLabel("Descripción: ");
            descripcionTareasDatos.add(descripcionLabel);
            descripcionTareasDatos.add(descripcionField);
            parametrosTareas.put("Descripción", descripcionField);

            JLabel personasAsignadasLabel = new JLabel("Lista Personas Asignadas: ");
            listaPersonasEnTareas.addAll(modelo.getPersonas());
            JList<Persona> personasAsignadasField = new JList<>(listaPersonasEnTareas);
            personasAsignadasField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            personasAsignadasField.setVisibleRowCount(4);
            JScrollPane personasAsignadasScroll = new JScrollPane(personasAsignadasField);
            descripcionTareasDatos.add(personasAsignadasLabel);
            descripcionTareasDatos.add(personasAsignadasScroll);
            parametrosTareas.put("Lista Personas Asignadas", personasAsignadasField);

            JComboBox<Persona> responsableField = new JComboBox<>(new Persona[]{});
            JLabel responsableLabel = new JLabel("Responsable: ");
            descripcionTareasDatos.add(responsableLabel);
            descripcionTareasDatos.add(responsableField);
            parametrosTareas.put("Responsable", responsableField);

            JComboBox<String> prioridadField = new JComboBox<>(modelo.getTiposPrioridad());
            prioridadField.setSelectedIndex(-1);
            prioridadField.setVisible(true);
            JLabel prioridadLabel = new JLabel("Prioridad: ");
            descripcionTareasDatos.add(prioridadLabel);
            descripcionTareasDatos.add(prioridadField);
            parametrosTareas.put("Prioridad", prioridadField);

            JTextField fechaCreacionField = new JTextField(20);
            fechaCreacionField.setEnabled(false);
            JLabel fechaCreacionLabel = new JLabel("Fecha de Creación: ");
            descripcionTareasDatos.add(fechaCreacionLabel);
            descripcionTareasDatos.add(fechaCreacionField);
            parametrosTareas.put("Fecha de Creación", fechaCreacionField);

            JTextField fechaFinalizacionField = new JTextField(20);
            fechaFinalizacionField.setEnabled(false);
            JLabel fechaFinalizacionLabel = new JLabel("Fecha de Finalización: ");
            descripcionTareasDatos.add(fechaFinalizacionLabel);
            descripcionTareasDatos.add(fechaFinalizacionField);
            parametrosTareas.put("Fecha de Finalización", fechaFinalizacionField);

            JCheckBox finalizadoField = new JCheckBox();
            JLabel finalizadoLabel = new JLabel("Finalizado: ");
            descripcionTareasDatos.add(finalizadoLabel);
            descripcionTareasDatos.add(finalizadoField);
            parametrosTareas.put("Finalizado", finalizadoField);

            JComboBox<String> resultadoField = new JComboBox<>(modelo.getTiposResultado());
            resultadoField.setSelectedIndex(-1);
            resultadoField.setVisible(true);
            JLabel resultadoLabel = new JLabel("Resultado: ");
            descripcionTareasDatos.add(resultadoLabel);
            descripcionTareasDatos.add(resultadoField);
            parametrosTareas.put("Resultado", resultadoField);

            JTextField etiquetasField = new JTextField(20);
            JLabel etiquetasLabel = new JLabel("Etiquetas: ");
            descripcionTareasDatos.add(etiquetasLabel);
            descripcionTareasDatos.add(etiquetasField);
            parametrosTareas.put("Etiquetas", etiquetasField);

            JTextField costeField = new JTextField(20);
            JLabel costeLabel = new JLabel("Coste: ");
            descripcionTareasDatos.add(costeLabel);
            descripcionTareasDatos.add(costeField);
            parametrosTareas.put("Coste", costeField);

            JTextField CosteFinalField = new JTextField(20);
            CosteFinalField.setEnabled(false);
            JLabel CosteFinalLabel = new JLabel("Coste Final: ");
            descripcionTareasDatos.add(CosteFinalLabel);
            descripcionTareasDatos.add(CosteFinalField);
            parametrosTareas.put("Coste Final", CosteFinalField);

            JComboBox<String> facturacionField = new JComboBox<>(modelo.getTiposFacturacion());
            facturacionField.insertItemAt("", 0);
            JLabel facturacionLabel = new JLabel("Facturación: ");
            descripcionTareasDatos.add(facturacionLabel);
            descripcionTareasDatos.add(facturacionField);
            parametrosTareas.put("Facturación", facturacionField);
        }
    }

    @Override
    public String getUbicacionFichero() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Archivo binario (.bin)", "bin");
        fileChooser.setFileFilter(filtro);
        fileChooser.showDialog(fileChooser, "Selecciona un fichero");
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            return ruta;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public CambioModeloTarea getTareaActual() {
        return modeloTarea.getTarea();
    }

    @Override
    public String getNuevoNombreProyecto() {
        return JOptionPane.showInputDialog(
                                ventana,
                                "Introduce un nombre para el proyecto");
    }

    @Override
    public String getNuevoDescripcion() {
        return ((JTextArea) parametrosTareas.get("Descripción")).getText();
    }

    @Override
    public List<Persona> getNuevoPersonasAsignadas() {
        return ((JList<Persona>) parametrosTareas.get("Lista Personas Asignadas")).getSelectedValuesList();
    }

    @Override
    public Persona getNuevoResponsable() {
        return (Persona) ((JComboBox) parametrosTareas.get("Responsable")).getSelectedItem();
    }

    @Override
    public String getNuevoPrioridad() {
        return (String) ((JComboBox) parametrosTareas.get("Prioridad")).getSelectedItem();
    }

    @Override
    public boolean getNuevoFinalizado() {
        return ((JCheckBox) parametrosTareas.get("Finalizado")).isSelected();
    }

    @Override
    public String getNuevoResultado() {
        return (String) ((JComboBox) parametrosTareas.get("Resultado")).getSelectedItem();
    }

    @Override
    public String getNuevoListaEtiquetas() {
        return ((JTextField) parametrosTareas.get("Etiquetas")).getText();
    }

    @Override
    public float getNuevoCoste() {
        return Float.parseFloat(((JTextField) parametrosTareas.get("Coste")).getText());
    }

    @Override
    public String getNuevoFacturacion() {
        return (String) ((JComboBox) parametrosTareas.get("Facturación")).getSelectedItem();
    }

    @Override
    public Map<String, String> getDatosNuevaTarea() {
        Map<String, String> mapaDatos = new HashMap<>();
        JTextField tituloTarea = new JTextField(10);
        JTextArea descripcion = new JTextArea(1, 10);
        JComboBox<String> prioridad = new JComboBox<>(modelo.getTiposPrioridad());
        JComboBox<String> resultado = new JComboBox<>(modelo.getTiposResultado());
        JTextField coste = new JTextField("0", 10);

        JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(0, 2));
        datos.add(new JLabel("Título: "));
        datos.add(tituloTarea);
        datos.add(new JLabel("Descripción: "));
        datos.add(descripcion);
        datos.add(new JLabel("Prioridad: "));
        datos.add(prioridad);
        datos.add(new JLabel("Resultado: "));
        datos.add(resultado);
        datos.add(new JLabel("Coste: "));
        datos.add(coste);

        int opcion =  JOptionPane.showConfirmDialog(
                ventana,
                datos,
                "Introduce valores para la tarea",
                JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            mapaDatos.put("Titulo", tituloTarea.getText());
            mapaDatos.put("Descripcion", descripcion.getText());
            mapaDatos.put("Prioridad", (String) prioridad.getSelectedItem());
            mapaDatos.put("Resultado", (String) resultado.getSelectedItem());
            mapaDatos.put("Coste", coste.getText());
            return mapaDatos;
        }
        return null;
    }

    @Override
    public boolean getNuevoDescuento() {
        return ((JCheckBox)parametrosPersonas.get("Descuento")).isSelected();
    }

    @Override
    public CambioModeloPersona getPersonaActual() {
        return modeloPersona.getPersona();
    }

    @Override
    public Map<String, String> getDatosNuevaPersona() {
        Map<String, String> mapaDatos = new HashMap<>();
        JTextField nombrePersona = new JTextField(10);
        JTextField correoPersona = new JTextField(10);

        JPanel datos = new JPanel(new GridLayout(0,2));
        datos.add(new JLabel("Nombre: "));
        datos.add(nombrePersona);
        datos.add(new JLabel("Correo: "));
        datos.add(correoPersona);

        int opcion =  JOptionPane.showConfirmDialog(
                ventana,
                datos,
                "Introduce valores para la persona",
                JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            mapaDatos.put("Nombre", nombrePersona.getText());
            mapaDatos.put("Correo", correoPersona.getText());
            return mapaDatos;
        }

        return null;
    }


    @Override
    public void importacionCorrecta() {
        paginaPrincipal();
    }

    @Override
    public void importacionFallida() {
        JOptionPane.showMessageDialog(ventana, "Error al importar el proyecto, inténtelo de nuevo");
    }

    @Override
    public void tareaAnyadida(String titulo) {
        modeloTablaTareas.fireTableDataChanged();
        listaTareasEnPersonas.addElement(titulo);
    }

    @Override
    public void personaAnyadida(Persona persona) {
        modeloTablaPersonas.fireTableDataChanged();
        listaPersonasEnTareas.addElement(persona);
    }

    @Override
    public void exportacionCorrecta() {
        int opcion = JOptionPane.showOptionDialog(
                ventana,
                "Seleccione una opción",
                "¿Qué quiere hacer?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] {"Continuar editando", "Salir"},
                "Continuar editando"
        );

        if (opcion == 1) {
            System.exit(0);
        }
    }

    @Override
    public void exportacionFallida() {
        JOptionPane.showMessageDialog(ventana, "Error al exportar el proyecto, inténtelo de nuevo");
    }

    @Override
    public void errorNombreProyecto() {
        JOptionPane.showMessageDialog(ventana, "El nombre del proyecto no puede estar vacío");
    }

    @Override
    public void guardadoCorrectoTareas() {
        actualizarTareas();
    }

    @Override
    public void tareaNoAnyadida(String mensajeError) {
        JOptionPane.showMessageDialog(ventana, "Error al añadir la tarea\n" + mensajeError);
    }

    @Override
    public void errorAlGuardar(String mensajeError) {
        JOptionPane.showMessageDialog(ventana, "Error al guardar los datos\n" + mensajeError);
    }

    @Override
    public void guardadoCorrectoPersonas() {
        actualizarPersonas();
    }

    private void actualizarTareas() {
        parametrosTareas.forEach((k, v) -> {
            switch (k) {
                case "Título" -> ((JTextField) v).setText(modeloTarea.getTitulo());
                case "Descripción" -> ((JTextArea) v).setText(modeloTarea.getDescripcion());
                case "Lista Personas Asignadas" -> setSelectedPersonas((JList) v);
                case "Responsable" -> setSelectedResponsable((JComboBox) v);
                case "Prioridad" -> ((JComboBox) v).setSelectedItem(MenuPrioridad.getTipo(modeloTarea.getPrioridad()));
                case "Fecha de Creación" -> ((JTextField) v).setText(modeloTarea.getFechaCreacion().toString());
                case "Fecha de Finalización" -> ((JTextField) v).setText((modeloTarea.getFechaFinalizacion()!=null)? String.valueOf(modeloTarea.getFechaFinalizacion()) :"");
                case "Finalizado" -> ((JCheckBox) v).setSelected(modeloTarea.isFinalizado());
                case "Resultado" -> ((JComboBox) v).setSelectedItem(getTipoResultado());
                case "Etiquetas" -> ((JTextField) v).setText(modeloTarea.getEtiquetas());
                case "Coste" -> ((JTextField) v).setText(String.valueOf(modeloTarea.getCoste()));
                case "Coste Final" -> ((JTextField) v).setText(String.valueOf(modeloTarea.calcularCosteFinal()));
                case "Facturación" -> ((JComboBox) v).setSelectedItem((modeloTarea.getFacturacion() != null) ? modeloTarea.getFacturacion().toString() : "");
            }
        });
        modeloTablaTareas.fireTableRowsUpdated(lsmTablaTareas.getMinSelectionIndex(), lsmTablaTareas.getMaxSelectionIndex());
    }

    private void actualizarPersonas() {
        parametrosPersonas.forEach((k, v) -> {
            switch (k) {
                case "Nombre" -> ((JTextField) v).setText(modeloPersona.getNombre());
                case "Correo" -> ((JTextField) v).setText(modeloPersona.getClave());
                case "Descuento" -> ((JCheckBox) v).setSelected(modeloPersona.tieneDescuento());
                case "Tareas" -> setSelectedTareas((JList) v);
            }
        });
        modeloTablaPersonas.fireTableRowsUpdated(lsmTablaPersonas.getMinSelectionIndex(), lsmTablaPersonas.getMaxSelectionIndex());
    }

    private void setSelectedResponsable(JComboBox<Persona> jResponsables) {
        jResponsables.removeAllItems();
        for (Persona persona : modeloTarea.getPersonasAsignadas())
            jResponsables.addItem(persona);
        jResponsables.insertItemAt(null, 0);
        jResponsables.setSelectedItem(modeloTarea.getResponsable());
    }

    private String getTipoResultado() {
        if (modeloTarea.getResultado() != null)
            return modeloTarea.getResultado().tipo();
        else
            return "";
    }

    private class EscuchadorTablaTareas implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        modeloTarea = modelo.identificarTarea((String) modeloTablaTareas.getValueAt(i,0));
                        actualizarTareas();
                    }
                }
            }
        }
    }

    private class EscuchadorTablaPersonas implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        modeloPersona = modelo.identificarPersona((String) modeloTablaPersonas.getValueAt(i,1));
                        actualizarPersonas();
                    }
                }
            }
        }
    }


    private class EscuchadorPrincipal implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            switch (texto) {
                case "Importar Proyecto" -> controlador.importar();
                case "Nuevo Proyecto" -> controlador.nuevoProyecto();
                case "Añadir tarea" -> controlador.nuevaTarea();
                case "Guardar tarea" -> controlador.guardarCambiosTarea();
                case "Añadir persona" -> controlador.nuevaPersona();
                case "Guardar persona" -> controlador.guardarCambiosPersona();
                case "Exportar proyecto" -> controlador.exportar();
            }
        }
    }

    private void setSelectedTareas(JList<String> jTareas) {
        List<String> tareasDeLasQueEsResponsable = modeloPersona.getTareasDeLasQueEsResponsable();
        int[] selecciones = new int[tareasDeLasQueEsResponsable.size()];
        for (int i = 0; i < tareasDeLasQueEsResponsable.size(); i++) {
            selecciones[i] = jTareas.getNextMatch(tareasDeLasQueEsResponsable.get(i), 0, Position.Bias.Forward);

        }
        jTareas.setSelectedIndices(selecciones);
    }

    public void setSelectedPersonas (JList<Persona> jPersonas) {
        List<Persona> personasAsignadas = modeloTarea.getPersonasAsignadas();
        int[] selecciones = new int[personasAsignadas.size()];
        for (int i = 0; i < personasAsignadas.size(); i++) {
            selecciones[i] = jPersonas.getNextMatch(personasAsignadas.get(i).toString(), 0, Position.Bias.Forward);
        }
        jPersonas.setSelectedIndices(selecciones);
    }

    public void setControlador(ControladorInicio controlador) {
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    private class PestanyaPersonas extends JPanel {
        public PestanyaPersonas() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new BorderLayout());

            modeloTablaPersonas = new ModeloTablaPersonas(modelo.getPersonas());

            JTable tablaTareas = new JTable(modeloTablaPersonas);

            JPanel contenedorListaPersonas = new JPanel();
            contenedorListaPersonas.setLayout(new BoxLayout(contenedorListaPersonas, BoxLayout.Y_AXIS));
            JButton botonAddPersona = new JButton("Añadir persona");
            botonAddPersona.addActionListener(new EscuchadorPrincipal());
            botonAddPersona.setAlignmentX(Component.LEFT_ALIGNMENT);
            contenedorListaPersonas.add(botonAddPersona);
            tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lsmTablaPersonas = tablaTareas.getSelectionModel();
            lsmTablaPersonas.addListSelectionListener(new EscuchadorTablaPersonas());
            contenedorListaPersonas.add(new JScrollPane(tablaTareas));

            JPanel contenedorDescripcionPersonas = new JPanel();
            contenedorDescripcionPersonas.setLayout(new BoxLayout(contenedorDescripcionPersonas, BoxLayout.Y_AXIS));

            JPanel descripcionPersonasDatos = new JPanel(new SpringLayoutSerializable());

            JTextField nombreField = new JTextField(20);
            nombreField.setMaximumSize( nombreField.getPreferredSize() );
            JLabel nombreLabel = new JLabel("Nombre: ");
            nombreField.setEnabled(false);
            descripcionPersonasDatos.add(nombreLabel);
            descripcionPersonasDatos.add(nombreField);
            parametrosPersonas.put("Nombre", nombreField);

            JTextField correoField = new JTextField(20);
            correoField.setMaximumSize( correoField.getPreferredSize() );
            JLabel correoLabel = new JLabel("Correo: ");
            correoField.setEnabled(false);
            descripcionPersonasDatos.add(correoLabel);
            descripcionPersonasDatos.add(correoField);
            parametrosPersonas.put("Correo", correoField);

            JCheckBox descuentoField = new JCheckBox();
            JLabel descuentoLabel = new JLabel("Descuento: ");
            descripcionPersonasDatos.add(descuentoLabel);
            descripcionPersonasDatos.add(descuentoField);
            parametrosPersonas.put("Descuento", descuentoField);

            JLabel tareasLabel = new JLabel("Tareas responsable: ");
            listaTareasEnPersonas.addAll(modelo.getTitulosTareas());
            JList<String> tareasField = new JList<>(listaTareasEnPersonas);
            tareasField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            tareasField.setVisibleRowCount(4);
            tareasField.setEnabled(false);
            JScrollPane tareasScroll = new JScrollPane(tareasField);
            descripcionPersonasDatos.add(tareasLabel);
            descripcionPersonasDatos.add(tareasScroll);
            parametrosPersonas.put("Tareas", tareasField);

            JButton guardar = new JButton("Guardar persona");
            guardar.addActionListener(new EscuchadorPrincipal());


            contenedorDescripcionPersonas.add(descripcionPersonasDatos);
            contenedorDescripcionPersonas.add(guardar);

            contenedorDescripcionPersonas.setBorder(new EmptyBorder(0, 10, 0, 0));

            layout.SpringUtilities.makeCompactGrid(descripcionPersonasDatos,
                    4, 2,
                    0, 0,
                    5, 5);


            add(contenedorListaPersonas, BorderLayout.CENTER);
            add(contenedorDescripcionPersonas, BorderLayout.EAST);
        }
    }
    private static class SpringLayoutSerializable extends SpringLayout implements Serializable {
        public SpringLayoutSerializable() {
            super();
        }
    }
}
