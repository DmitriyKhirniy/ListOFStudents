import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.SimpleDateFormat;
import University.*;
public class TableModel extends AbstractTableModel {

    private static JLabel error;
    private List<Student> students;

    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public TableModel( List<Student> data, JLabel  _error )
    {
        error= _error;
        this.students = data;
    };

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
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
                result = "Номер студенського";
                break;
            case 7:
                result = "Курс";
                break;
            case 8:
                result = "Рейтинговій бал";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return students.get(r).hashCode();
            case 1:
                return students.get(r).getName();
            case 2:
                return students.get(r).getFaculty();
            case 3:
                //return students.get(r).getDepartment();
                return students.get(r).getDepartment();
            case 4:
                return students.get(r).getAge();
            case 5:
                return format.format( students.get(r).getDate().getTime() );
            case 6:
                return students.get(r).getNum().getStdNum();
            case 7:
                return students.get(r).getCource().getCource();
            case 8:
                return students.get(r).getRatingPoint();
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
                        students.get(rowIndex).setNames(new Names(firstName, middleName, lastName));
                    break;
                case 2:
                    System.out.println( value.toString());
                    students.get(rowIndex).setFaculty( value.toString() );
                    break;
                case 3:
                    System.out.println( value.toString());
                    students.get(rowIndex).setDepartment( value.toString() );
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
                        students.get(rowIndex).setDate(new GregorianCalendar(_time[2], _time[1], _time[0]));
                        students.get(rowIndex).setAge(calculateAge(students.get(rowIndex).getDate().getTime()));
                        for (int i = 0; i < getColumnCount(); i++) fireTableCellUpdated(rowIndex, i);
                    }
                    break;
                case 6:
                    String num = checkNum( value.toString() );
                    if(num!= null ) students.get(rowIndex).setStudentNum(value.toString());
                    break;
                case 7:
                    String cource = checkCource(value.toString());
                    if (cource != null) students.get(rowIndex).setCource(cource);
                    break;
                case 8:
                    Double ratingPoint = checkRatingPoint( Double.valueOf(value.toString()) );
                    if (ratingPoint != null) students.get(rowIndex).setRatingPoint(ratingPoint);
                    break;
            }

        }
        catch( Exception exc ){
            error.setText(exc.getMessage() );
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        API.studentsCopy.set(rowIndex, API.students.get(rowIndex) );
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

    private static String checkCource( String cource )
    {
        switch( cource ){
            case "I": return "I";
            case "II": return "II";
            case "III": return "II";
            case "IV": return "IV";
            case "V": return "V";
            case "VI": return "VI";
            case "1": return "I";
            case "2": return "II";
            case "3": return "III";
            case "4": return "IV";
            case "5": return "V";
            case "6": return "VI";
            default:
                error.setText("Error.Can't set cource.");
                return null;
        }
    };

    private static Double checkRatingPoint( double point )
    {
        if(point < 100 || point > 220) {
            error.setText("Error.Undefined rating point.");
            return null;
        }
        else{
            return point;
        }
    };

    private static String checkNum( String num )
    {
        String check = new String( num.replace( "", "") );

        char[] numbers = {0,1,2,3,4,5,6,7,8,9};

        if( check.length() != 12 ||  check.charAt(3)!='№'  ) {
            error.setText("Error.Undefined format of student number.");
            return null;
        }
        else return num;
    };

    public JComboBox getComboBox( String checked )
    {
        JComboBox comboFI = new JComboBox();
        comboFI.addItem("Програмна інженерія");
        comboFI.addItem("Інформатика");
        comboFI.addItem("Прикладна матем.");

        JComboBox comboFEN = new JComboBox();
        comboFEN.addItem("Фінанси");
        comboFEN.addItem("Економ. теорія");
        comboFEN.addItem("Маркетинг");



        return comboFEN;
    };
}
