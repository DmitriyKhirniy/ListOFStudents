import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent component = new JTextField();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int rowIndex, int vColIndex) {

        ((JTextField) component).setText((String) value);

        return getComboBox( API.students.get(rowIndex).getFaculty() ,rowIndex);
    }

    public Object getCellEditorValue() {
        return ((JTextField) component).getText();
    }
    private JComboBox getComboBox( String str ,int row)
    {
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
    };
}
