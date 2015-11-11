/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Presentation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * @author André
 */
public class TableModel extends AbstractTableModel {

    private final String[] header;
    private final ArrayList<ArrayList<Object>> grid;

    public TableModel(String[] header) {
        this.header = header;
        grid = new ArrayList<>();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(ArrayList row) {
        grid.add(row);
        fireTableDataChanged();
    }

    public void deleteRow(int index) {
        grid.remove(index);
        fireTableDataChanged();
    }

    public void resetTable() {
        grid.clear();
    }

    @Override
    public int getRowCount() {
        return grid.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return grid.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object cellValue, int rowIndex, int columnIndex) {
        grid.get(rowIndex).set(columnIndex, cellValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
