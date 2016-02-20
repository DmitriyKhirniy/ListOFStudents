package University;

import java.util.GregorianCalendar;

public class Lecturer extends Person {

    private String position;
    private String faculty;
    private String department;
    private University university;
    private String responsibility;
    private int id;

    public Lecturer( String firstName , String middleName , String lastName, GregorianCalendar birthDate, University university , String position, String faculty, String department, String responsibility )
    {
        super( new Names( firstName , middleName, lastName ), birthDate);
        this.id = this.hashCode();
        this.university = university;
        this.position = position;
        this.faculty = faculty;
        this.department = department;
        this.responsibility = responsibility;
    };

    public int getId()
    {
        return this.id;
    };

    public String getPosition()
    {
        return this.position;
    };

    public void setPosition( String newPosition )
    {
        this.position = newPosition;
    };

    public String getFaculty()
    {
        return this.faculty;
    };

    public void setFaculty( String newFaculty )
    {
        this.faculty = newFaculty;
    };

    public String getDepartment()
    {
        return this.department;
    };

    public void setDepartment( String newDepartment )
    {
        this.department = newDepartment;
    };

    public String getResponsibility()
    {
        return this.responsibility;
    };

    public void setResponsibility( String newRes )
    {
        this.responsibility = newRes;
    };
}
