package University;

import java.util.ArrayList;

public class Department {
    private String title;
   // private Lecturer dean;
    private String faculty;


    public Department( String title, String faculty)
    {

        this.title = title;
        //this.dean = dean;
        this.faculty = faculty;
    };

    public String getTitle()
    {
        return this.title;
    };

    public void setTitle( String name )
    {
        this.title = name;
    };

}

