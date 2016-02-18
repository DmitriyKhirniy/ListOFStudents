import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent component = new JComboBox<String>();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int rowIndex, int vColIndex) {
        component = getComboBox( API.students.get(rowIndex).getFaculty(), rowIndex);
        return component;
    }

    public Object getCellEditorValue() {
        return ((JComboBox) component).getSelectedItem();
    }

    private JComboBox getComboBox( String str, int rowIndex)
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
/*
        JComboBox comboFI = new JComboBox();
        comboFI.addItem("Програмна інженерія");
        comboFI.addItem("Інформатика");
        comboFI.addItem("Прикладна матем.");

        JComboBox comboFEN = new JComboBox();
        comboFEN.addItem("Фінанси");
        comboFEN.addItem("Економ. теорія");
        comboFEN.addItem("Маркетинг");

        JComboBox comboFSNST= new JComboBox();
        comboFSNST.addItem("Політологія");
        comboFSNST.addItem("Соціальна робота");
        comboFSNST.addItem("Соціологія");
        comboFSNST.addItem("Психологія");
        comboFSNST.addItem("Журналістика");

        JComboBox comboFPN = new JComboBox();
        comboFPN.addItem("Правознавство");

        JComboBox comboFGN = new JComboBox();
        comboFGN.addItem("Історія");
        comboFGN.addItem("Культурологія");
        comboFGN.addItem("Філологія");
        comboFGN.addItem("Філософія");

        JComboBox comboFPRN = new JComboBox();
        comboFPRN.addItem("Хімія");
        comboFPRN.addItem("Біологія");
        comboFPRN.addItem("Екологія");
        comboFPRN.addItem("Фізика");


        JComboBox comboDefault = new JComboBox();
        comboDefault.addItem("Не визначено");




        switch( str )
        {
            case "ФІ":
                return comboFI;
            case "ФЕН":
                return comboFEN;
            case "ФСНСТ":
                return comboFSNST;
            case "ФПН":
                return comboFPN;
            case "ФГН":
                return  comboFGN;
            case "ФПРН":
                return comboFPRN;
            default: return comboDefault;
        }
*/
    };
}
