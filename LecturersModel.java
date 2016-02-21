import University.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

public class LecturersModel extends AbstractTableModel {
    private static List<Lecturer> lecturers;
    private static JLabel error;

    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public LecturersModel(List<Lecturer> lecturers, JLabel error)
    {
        this.lecturers = lecturers;
        this.error = error;
    }

    @Override
    public int getRowCount() {
        return lecturers.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "ID";
                break;
            case 1:
                result = "Прзвище, ім'я ,по батькові";
                break;
            case 2:
                result = "Факультет";
                break;
            case 3:
                result = "Напрямок";
                break;
            case 4:
                result = "Вік";
                break;
            case 5:
                result = "Дата народження";
                break;
            case 6:
                result = "Звання";
                break;
            case 7:
                result = "Що викладає";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                 return lecturers.get(r).getPersonId();
            case 1:
                 return lecturers.get(r).getName();
            case 2:
                 return lecturers.get(r).getFaculty();
            case 3:
                 return lecturers.get(r).getDepartment();
            case 4:
                 return lecturers.get(r).getAge();
            case 5:
                 return format.format( lecturers.get(r).getDate().getTime() );
            case 6:
                 return lecturers.get(r).getPosition();
            case 7:
                return lecturers.get(r).getResponsibility();
            default:
                return "";
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        if( column == 0 || column == 4 )return false;
        return true;
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 1:
                    StringTokenizer str = new StringTokenizer(value.toString());
                    String lastName = str.nextToken();
                    String firstName = str.nextToken();
                    String middleName = str.nextToken();
                     if (checkName(lastName) != null && checkName(firstName) != null && checkName(middleName) != null)
                     lecturers.get(rowIndex).setNames(new Names(firstName, middleName, lastName));
                    break;
                case 2:
                     lecturers.get(rowIndex).setFaculty( value.toString() );
                    break;
                case 3:
                    lecturers.get(rowIndex).setDepartment( value.toString() );
                    break;
                case 5:
                    String time = value.toString();
                    int[] _time = new int[3];
                    if (time.length() == 10) {
                        _time[0] = Integer.valueOf(time.substring(0, 2));
                        _time[1] = Integer.valueOf(time.substring(3, 5));
                        _time[2] = Integer.valueOf(time.substring(6, 10));
                    }
                      Integer age = calculateAge(new GregorianCalendar(_time[2], _time[1], _time[0]).getTime());
                      if (age != null) {
                          lecturers.get(rowIndex).setDate(new GregorianCalendar(_time[2], _time[1], _time[0]));
                          lecturers.get(rowIndex).setAge(calculateAge(lecturers.get(rowIndex).getDate().getTime()));
                          for (int i = 0; i < getColumnCount(); i++) fireTableCellUpdated(rowIndex, i);
                      }
                    break;
                case 6:
                    lecturers.get(rowIndex).setPosition(value.toString());
                    break;
                case 7:
                    lecturers.get(rowIndex).setResponsibility(value.toString());
                    break;
            }

        }
        catch( Exception exc ){
            error.setText(exc.getMessage() );
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    private static String checkName( String string)
    {
        if( string.length() < 3 || string.length() > 15 )
        {
            error.setText("Eror,Undefined size of name.");
            return null;
        }
        else if( string.toUpperCase().charAt(0) != string.charAt(0) )
        {
            error.setText("Eror,First letter of the name must be in uppercase.");
            return null;
        }
        else return string;
    };

    private static Integer calculateAge(final Date birthday)
    {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(birthday);
        // include day of birth
        dob.add(Calendar.DAY_OF_MONTH, -1);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if( age < 14 || age > 75 )
        {
            error.setText("Eror,Input another date of birth.");
            return null;
        }
        return age;
    }
}

