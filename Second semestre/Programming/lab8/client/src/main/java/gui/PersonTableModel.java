package gui;

import collectionManager.ClientCollectionManager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PersonTableModel extends AbstractTableModel {
    private ClientCollectionManager collectionManager;
    private List<String> columnHeader;

    public PersonTableModel(ClientCollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.columnHeader = new ArrayList<>(13);
        updateColumnHeaders();
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return collectionManager.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return collectionManager.fieldCount();
    }

    /**
     * Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
     *
     * @param columnIndex the column being queried
     * @return the Object.class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return collectionManager.getField(0, columnIndex).getClass();
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return collectionManager.getField(rowIndex, columnIndex);
    }

    /**
     * Returns a default name for the column using spreadsheet conventions:
     * A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
     * returns an empty string.
     *
     * @param column the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return columnHeader.get(column);
    }

    public void updateColumnHeaders(){
        columnHeader.clear();
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.id"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.name"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.coordinates_x"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.coordinates_y"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.creation_date"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.height"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.birthday"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.eye_color"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.hair_color"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.location_x"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.location_y"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.location_name"));
        columnHeader.add(ResourceBundle.getBundle("messages").getString("column.owner"));
    }
}
