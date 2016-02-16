package University;

import java.util.ArrayList;

public class Department {
   // public ArrayList<Student> students;
   // private ArrayList<Lecturer> lecturers;
    private String title;
   // private Lecturer dean;
    private String faculty;

  //  private int stdQuantity;
   // private int lecQuantity;

    public Department( String title, String faculty)
    {
       // students = new ArrayList<Student>();
        //lecturers = new ArrayList<Lecturer>();
        this.title = title;
        //this.dean = dean;
        this.faculty = faculty;
    };

   /* public Department( ArrayList<Student> students , ArrayList<Lecturer> lecturers,  String name , Lecturer dean)
    {
        this.students = students;
        this.lecturers = lecturers;
        this.name = name;
        this.dean = dean;
    };
*/
    public String getTitle()
    {
        return this.title;
    };

    public void setTitle( String name )
    {
        this.title = name;
    };
/*
    public Lecturer getDean()
    {
        return this.dean;
    };

    public void setDean( Lecturer lecturer )
    {
        this.dean = lecturer;
    };

    public void addToStudents( Student student ){
        students.add( student );
    };

    public void addToLecturers( Lecturer lecturer )
    {
        lecturers.add( lecturer );
    };

    public ArrayList<Student> getAllStudents()
    {
        return this.students;
    };

    public String[][] getStudents()
    {
        String[][] data= new String[ students.size() ][9];
        for(int i=0;i<students.size();i++)
        {
            data[i][0] = students.get( i ).getName().toString();
            data[i][1] = faculty;
            data[i][2] = getTitle();
            data[i][3] = String.valueOf( students.get( i ).getAge() );
            data[i][4] = students.get( i ).getBirthDate().getDate();
            data[i][5] = students.get( i ).getNum().getStdNum();
            data[i][6] = students.get( i ).getCource().getCource();
            data[i][7] = String.valueOf( students.get( i ).getRatingPoint() );
        };

        return data;
    };

*/
}

