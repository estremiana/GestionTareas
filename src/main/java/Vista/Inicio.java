package Vista;

import Controlador.ControladorInicio;
import Modelo.*;
import Modelo.Menu.MenuPrioridad;
import Modelo.Menu.MenuResultado;
import Modelo.Resultado.Biblioteca;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Inicio implements InterrogaVista, InformaVista {
    private ControladorInicio controlador;
    private InterrogaModelo modelo;
    private InterrogaModeloTarea modeloTarea;
    private ModeloTabla modeloTabla;
    private JTable tablaTareas;
    private JFrame ventana;
    private Map<String, Accessible> parametrosTareas;

    public void creaInicio() {
        parametrosTareas = new HashMap<>();
        SwingUtilities.invokeLater(this::inicio);
    }

    private void inicio() {
        ventana = new JFrame("Proyecto");

        JButton botonImportar = new JButton("Importar Proyecto");
        botonImportar.addActionListener(new Escuchador());
        JButton botonNuevo = new JButton("Nuevo Proyecto");
        botonNuevo.addActionListener(new Escuchador());

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

        ventana.add(fondo);
        ventana.setSize(450, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    private void menu() {
        ventana.setVisible(false);
        ventana = new JFrame(modelo.nombreProyecto());

        JPanel fondo = new JPanel();
        fondo.setBorder(new EmptyBorder(10, 10, 10, 10));
        fondo.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();



        //List<Tarea> prueba = new ArrayList<Tarea>();
        //Tarea tarea = new Tarea("prueba", "hola", MenuPrioridad.ALTA, new Biblioteca(), new ArrayList<>());
        //tarea.setResponsable(new Persona("dani", "daniemail"));
        //prueba.add(tarea);
        //tarea = new Tarea("prueba2", "ha", MenuPrioridad.ALTA, new Biblioteca(), new ArrayList<>());
        //tarea.setResponsable(new Persona("oscar", "oscaremail"));
        //prueba.add(tarea);
        //modeloTabla = new ModeloTabla(prueba);
        modeloTabla = new ModeloTabla(modelo.getTareas());


        JTable tablaTareas = new JTable(modeloTabla);

        JPanel contenedorTareas = new JPanel();
        contenedorTareas.setLayout(new BoxLayout(contenedorTareas, BoxLayout.Y_AXIS));
        JButton botonAddTarea = new JButton("Añadir tarea");
        botonAddTarea.addActionListener(new Escuchador());
        botonAddTarea.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedorTareas.add(botonAddTarea);
        tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = tablaTareas.getSelectionModel();
        selectionModel.addListSelectionListener(new EscuchadorTabla());
        contenedorTareas.add(new JScrollPane(tablaTareas));


        JPanel descripcionTareas = new JPanel();
        descripcionTareas.setLayout(new BoxLayout(descripcionTareas, BoxLayout.PAGE_AXIS));
        //descripcionTareas.setLayout(new GridLayout(13,2));

        JPanel containerTituloTarea = new JPanel();
        JTextField tituloField = new JTextField(20);
        JLabel tituloLabel = new JLabel("Título: ");
        containerTituloTarea.setLayout(new FlowLayout());
        containerTituloTarea.add(tituloLabel);
        containerTituloTarea.add(tituloField);
        parametrosTareas.put("Título", tituloField);

        Container containerDescripcionTarea = new Container();
        JTextField descripcionField = new JTextField(20);
        JLabel descripcionLabel = new JLabel("Descripción: ");
        containerDescripcionTarea.setLayout(new FlowLayout());
        containerDescripcionTarea.add(descripcionLabel);
        containerDescripcionTarea.add(descripcionField);
        parametrosTareas.put("Descripción", descripcionField);

        Container containerPersonasAsignadasTarea = new Container();
        JList<Persona> personasAsignadasField = new JList<>(modelo.getPersonas().toArray(new Persona[]{}));
        personasAsignadasField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        personasAsignadasField.setVisibleRowCount(4);
        JScrollPane personasAsignadasScroll = new JScrollPane(personasAsignadasField);
        JLabel personasAsignadasLabel = new JLabel("Lista Personas Asignadas: ");
        containerPersonasAsignadasTarea.setLayout(new FlowLayout());
        containerPersonasAsignadasTarea.add(personasAsignadasLabel);
        containerPersonasAsignadasTarea.add(personasAsignadasScroll);
        parametrosTareas.put("Lista Personas Asignadas", personasAsignadasField);

        Container containerResponsableTarea = new Container();
        JComboBox<Persona> responsableField = new JComboBox<>(new Persona[]{});

        JLabel responsableLabel = new JLabel("Responsable: ");
        containerResponsableTarea.setLayout(new FlowLayout());
        containerResponsableTarea.add(responsableLabel);
        containerResponsableTarea.add(responsableField);
        parametrosTareas.put("Responsable", responsableField);

        Container containerPrioridadTarea = new Container();
        JComboBox<String> prioridadField = new JComboBox<>(modelo.getTiposPrioridad());
        prioridadField.setVisible(true);
        JLabel prioridadLabel = new JLabel("Prioridad: ");
        containerPrioridadTarea.setLayout(new FlowLayout());
        containerPrioridadTarea.add(prioridadLabel);
        containerPrioridadTarea.add(prioridadField);
        parametrosTareas.put("Prioridad", prioridadField);

        Container containerFechaCreacionTarea = new Container();
        JTextField fechaCreacionField = new JTextField(20);
        fechaCreacionField.setEnabled(false);
        JLabel fechaCreacionLabel = new JLabel("Fecha de Creación: ");
        containerFechaCreacionTarea.setLayout(new FlowLayout());
        containerFechaCreacionTarea.add(fechaCreacionLabel);
        containerFechaCreacionTarea.add(fechaCreacionField);
        parametrosTareas.put("Fecha de Creación", fechaCreacionField);

        Container containerFechaFinalizacionTarea = new Container();
        JTextField fechaFinalizacionField = new JTextField(20);
        fechaFinalizacionField.setEnabled(false);
        JLabel fechaFinalizacionLabel = new JLabel("Fecha de Finalización: ");
        containerFechaFinalizacionTarea.setLayout(new FlowLayout());
        containerFechaFinalizacionTarea.add(fechaFinalizacionLabel);
        containerFechaFinalizacionTarea.add(fechaFinalizacionField);
        parametrosTareas.put("Fecha de Finalización", fechaFinalizacionField);

        Container containerFinalizadoTarea = new Container();
        JCheckBox finalizadoField = new JCheckBox();
        JLabel finalizadoLabel = new JLabel("Finalizado: ");
        containerFinalizadoTarea.setLayout(new FlowLayout());
        containerFinalizadoTarea.add(finalizadoLabel);
        containerFinalizadoTarea.add(finalizadoField);
        parametrosTareas.put("Finalizado", finalizadoField);

        Container containerResultadoTarea = new Container();
        JComboBox<String> resultadoField = new JComboBox<>(modelo.getTiposResultado());
        resultadoField.setVisible(true);
        JLabel resultadoLabel = new JLabel("Resultado: ");
        containerResultadoTarea.setLayout(new FlowLayout());
        containerResultadoTarea.add(resultadoLabel);
        containerResultadoTarea.add(resultadoField);
        parametrosTareas.put("Resultado", resultadoField);

        Container containerEtiquetasTarea = new Container();
        JTextField etiquetasField = new JTextField(20);
        JLabel etiquetasLabel = new JLabel("Etiquetas: ");
        containerEtiquetasTarea.setLayout(new FlowLayout());
        containerEtiquetasTarea.add(etiquetasLabel);
        containerEtiquetasTarea.add(etiquetasField);
        parametrosTareas.put("Etiquetas", etiquetasField);

        Container containerCosteTarea = new Container();
        JTextField costeField = new JTextField(20);
        JLabel costeLabel = new JLabel("Coste: ");
        containerCosteTarea.setLayout(new FlowLayout());
        containerCosteTarea.add(costeLabel);
        containerCosteTarea.add(costeField);
        parametrosTareas.put("Coste", costeField);

        Container containerCosteFinal = new Container();
        JTextField CosteFinalField = new JTextField(20);
        CosteFinalField.setEnabled(false);
        JLabel CosteFinalLabel = new JLabel("Coste Final: ");
        containerCosteFinal.setLayout(new FlowLayout());
        containerCosteFinal.add(CosteFinalLabel);
        containerCosteFinal.add(CosteFinalField);
        parametrosTareas.put("Coste Final", CosteFinalField);

        Container containerFacturacionTarea = new Container();
        JTextField facturacionField = new JTextField(20);
        JLabel facturacionLabel = new JLabel("Facturación: ");
        containerFacturacionTarea.setLayout(new FlowLayout());
        containerFacturacionTarea.add(facturacionLabel);
        containerFacturacionTarea.add(facturacionField);
        parametrosTareas.put("Facturación", facturacionField);

        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(new Escuchador());



        descripcionTareas.add(containerTituloTarea);
        descripcionTareas.add(containerDescripcionTarea);
        descripcionTareas.add(containerPersonasAsignadasTarea);
        descripcionTareas.add(containerResponsableTarea);
        descripcionTareas.add(containerPrioridadTarea);
        descripcionTareas.add(containerFechaCreacionTarea);
        descripcionTareas.add(containerFechaFinalizacionTarea);
        descripcionTareas.add(containerFinalizadoTarea);
        descripcionTareas.add(containerResultadoTarea);
        descripcionTareas.add(containerEtiquetasTarea);
        descripcionTareas.add(containerCosteTarea);
        descripcionTareas.add(containerCosteFinal);
        descripcionTareas.add(containerFacturacionTarea);
        descripcionTareas.add(guardar);

        descripcionTareas.setBorder(BorderUIResource.getBlackLineBorderUIResource());

        gbc.gridx = 0;
        gbc.gridy = 0;
        fondo.add(contenedorTareas, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        fondo.add(descripcionTareas, gbc);

        ventana.getContentPane().add(fondo);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
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
            System.out.println(ruta);
            return ruta;
        } catch (NullPointerException ex) {
            System.out.println("No se ha seleccionado ningún archivo");
            return null;
        }
    }

    @Override
    public String getNuevoNombreProyecto() {
        System.out.println("hola");
        return JOptionPane.showInputDialog(
                ventana,
                "Introduce un nombre para el proyecto");
    }

    @Override
    public void cargaCorrecta() {
        menu();
    }

    @Override
    public void cargaFallida() {
        JDialog jd = new JDialog(ventana);
        JLabel jl = new JLabel("Error al cargar el proyecto, inténtelo de nuevo");
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setBorder(new EmptyBorder(10,10,10,10));
        jd.add(jl);
        jd.pack();
        jd.setLocationRelativeTo(ventana);
        jd.setVisible(true);
    }

    @Override
    public void anyadirTareaVacia() {
        actualizar();
    }

    private void actualizar() {
        parametrosTareas.forEach((k, v) -> {

            switch (k) {
                case "Título" -> ((JTextField) v).setText(modeloTarea.getTitulo());
                case "Descripción" -> ((JTextField) v).setText(modeloTarea.getDescripcion());
                case "Lista Personas Asignadas" -> {
                    ((JList) v).clearSelection();
                    for (Persona persona : modeloTarea.getPersonasAsignadas())
                        ((JList) v).setSelectedValue(persona, false);
                }
                case "Responsable" -> {
                    ((JComboBox) v).removeAllItems();
                    for (Persona persona : modeloTarea.getPersonasAsignadas())
                        ((JComboBox) v).addItem(persona);
                    ((JComboBox) v).setSelectedItem(modeloTarea.getResponsable());
                }
                case "Prioridad" -> ((JComboBox) v).setSelectedItem(MenuPrioridad.getTipo(modeloTarea.getPrioridad()));
                case "Fecha de Creación" -> ((JTextField) v).setText(modeloTarea.getFechaCreacion().toString());
                case "Fecha de Finalización" -> ((JTextField) v).setText((modeloTarea.getFechaFinalizacion()!=null)? String.valueOf(modeloTarea.getFechaFinalizacion()) :"");
                case "Finalizado" -> ((JCheckBox) v).setSelected(modeloTarea.isFinalizado());
                case "Resultado" ->  {((JComboBox) v).setSelectedItem(modeloTarea.getResultado().tipo()); }
                //case "Etiquetas" -> ((JTextField) v).setText(modeloTarea.getDescripcion());
                case "Coste" -> ((JTextField) v).setText(String.valueOf(modeloTarea.getCoste()));
                case "Coste Final" -> ((JTextField) v).setText(String.valueOf(modeloTarea.getCosteFinal()));
                case "Facturación" -> ((JTextField) v).setText((modeloTarea.getFacturacion()!=null)? String.valueOf(modeloTarea.getFacturacion()) :"");
            }

        });
    }

    private class EscuchadorTabla implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        System.out.println(i);
                        modeloTarea = modelo.identificarTarea((String) modeloTabla.getValueAt(i,0));
                        System.out.println(modeloTarea.getTitulo());
                        actualizar();
                    }
                }
            }
        }
    }


    private class Escuchador implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            switch (texto) {
                case "Importar Proyecto" -> controlador.importar();
                case "Nuevo Proyecto" -> controlador.nuevoProyecto();
                case "Añadir tarea" -> controlador.nuevaTarea();
                case "Guardar" -> controlador.guardarCambiosTarea();
            }
        }

    }

    public void setControlador(ControladorInicio controlador) {
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setModeloTabla(ModeloTabla modeloTabla) {
        this.modeloTabla = modeloTabla;
    }
}
