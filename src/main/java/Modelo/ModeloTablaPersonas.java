package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTablaPersonas extends AbstractTableModel {
    private final String[] nombreColumnas = {"Nombre", "Correo", "Descuento"};

    private List<Persona> datos;

    public ModeloTablaPersonas(List<Persona> personas) {
        this.datos = personas;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Persona persona = datos.get(row);
        return switch (column) {
            case 0 -> persona.getNombre();
            case 1 -> persona.getClave();
            case 2 -> persona.tieneDescuento();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];
    }

}
