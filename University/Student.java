package University;

import java.util.GregorianCalendar;

public class Student extends Person implements Comparable{

    private StdNum studentNum;
    private Cource cource;
    private double ratingPoint;
    private University university;
    private String faculty;
    private String department;

    public Student( String firstName , String middleName , String lastName,GregorianCalendar birthDate , String studentNum ,String cource , double mark, University univer, String faculty, String department )
    {
        super(new Names( firstName , middleName, lastName ), birthDate);
        this.cource = new Cource( cource );
        this.ratingPoint = mark;
        this.university = univer;
        this.faculty = faculty;
        this.department = department;
        addNum( studentNum );
    };

    public int compareTo(Object obj) {
        Student entry = (Student) obj;

        int result = super.getName().getLastName().compareTo(entry.getName().getLastName());
        if(result != 0) {
            return result;
        }
        return 0;
    }

    public StdNum getNum()
    {
        return this.studentNum;
    };

    public Cource getCource()
    {
        return this.cource;
    };

    public void setRatingPoint(double nPoint)
    {
        this.ratingPoint = nPoint;
    };

    public void setCource( String cource )
    {
        this.cource = new Cource(cource);
    };

    public double getRatingPoint()
    {
        return this.ratingPoint;
    };

    public void setStudentNum( String num )
    {
        this.studentNum = new StdNum( num );
    };

    private void addNum( String num )
    {
        StdNum current = new StdNum( num );
        System.out.print(current.getStdNum());
        this.studentNum = current;
        this.university.addStdNum( current );
    };
    public String getFaculty()
    {
        return this.faculty;
    };
    public void setFaculty( String faculty )
    {
        this.faculty = faculty;
    };
    public String getDepartment()
    {
        return this.department;
    };
    public void setDepartment( String department )
    {
        this.department = department;
    };
}


