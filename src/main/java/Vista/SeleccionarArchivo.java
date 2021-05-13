package Vista;

import javax.swing.*;

public class SeleccionarArchivo implements InterrogaVista{
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
}
