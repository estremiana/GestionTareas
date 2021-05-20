package Modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {
    private final String[] nombreColumnas = {"Tarea", "Responsable", "Fecha", "Coste", "Finalizado"};

    private List<Tarea> datos;

    public ModeloTabla(List<Tarea> tareas) {
        this.datos = tareas;
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
        Tarea tarea = datos.get(row);
        return switch (column) {
            case 0 -> tarea.getTitulo();
            case 1 -> tarea.getResponsable().nombre;
            case 2 -> tarea.getFechaCreacion();
            case 3 -> tarea.getCoste();
            case 4 -> tarea.isFinalizado();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];
    }

}
