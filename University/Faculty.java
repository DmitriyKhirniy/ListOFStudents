package University;

import java.util.ArrayList;

public class Faculty {
    private String title;
    private ArrayList<Department> departments;
    //private Lecturer dean;

    public Faculty(String title )
    {
        departments = new ArrayList<Department>();
        this.title = title;
       // this.dean = dean;
    };

    public String getTitle()
    {
        return this.title;
    };

    public void setTitle( String nTitle )
    {
        this.title = nTitle ;
    };

   /* public Lecturer getDean()
    {
        return this.dean;
    };

    public void changeDean( Lecturer newDean )
    {
        this.dean = dean;
    };
 */
    public void addDepartment( Department department )
    {
        departments.add( department );
    };

    public void deleteDepartment( int index )
    {
        departments.remove( index );
        departments.trimToSize();
    };

    public boolean deleteDepartment( Department department )
    {
        boolean deleteSuccess = false;

        for(int i=0;i<departments.size();i++)
        {
            if( departments.get( i ).getTitle().equals( department.getTitle() ) )
            {
                departments.remove( i );
                departments.trimToSize();
                deleteSuccess = true;
                break;
            };
        };

        return deleteSuccess;
    };

    public ArrayList<Department> getDepartments()
    {
        return this.departments;
    };

    public Department getDepartment(int index)
    {
        return this.departments.get(index);
    };
}

