package Vista;

import Controlador.ControladorInicio;
import Controlador.EscuchadoraBotonImportar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Inicio implements InterrogaVista, InformaVista {
    private ControladorInicio controlador;
    private JFrame ventana;
    public void creaInicio() {
        SwingUtilities.invokeLater(this::inicio);
    }

    private void inicio() {
        ventana = new JFrame("Proyecto");

        JButton botonImportar = new JButton("Importar Proyecto");
        botonImportar.addActionListener(new Escuchador());
        JButton botonNuevo = new JButton("Nuevo Proyecto");

        JPanel fondo = new JPanel();
        fondo.setBorder(new EmptyBorder(10, 10, 10, 10));
        //fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
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

    @Override
    public String getEntrada() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
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
    public void cargaCorrecta() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("A:"));
        panel.add(new JTextField(12));
        panel.add(new JLabel("B:"));
        panel.add(new JTextField(12));
        JOptionPane.showConfirmDialog(ventana, panel);
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

    private class Escuchador implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            switch (texto) {
                case "Importar Proyecto" -> controlador.importar();
                case "Nuevo" -> controlador.nuevo();

            }
        }

    }

    public void setControlador(ControladorInicio controlador) {
        this.controlador = controlador;
    }
}
