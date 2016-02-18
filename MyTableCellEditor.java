import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent component = new JComboBox<String>();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int rowIndex, int vColIndex) {
        component = getComboBox( API.students.get(rowIndex).getFaculty());
        return component;
    }

    public Object getCellEditorValue() {
        return ((JComboBox) component).getSelectedItem();
    }

    private JComboBox getComboBox( String str )
    {

        List<JComboBox<String>> list = new ArrayList<JComboBox<String>>();


        for(int i=0;i<API.university.faculties.size();i++)
        {
            JComboBox current = new JComboBox();
           for(int j=0;j<API.university.faculties.get(i).getDepartments().size();j++)
           {
            current.addItem( API.university.faculties.get(i).getDepartments().get(j).getTitle() );
           }

            list.add( current );
        }


        for(int i=0;i<list.size();i++)
        {
            if( API.university.faculties.get(i).getTitle().equals( str ) )
            {
                return list.get(i);
            }
        }
        return null;
    };
}
