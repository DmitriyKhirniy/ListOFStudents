package University;

import java.util.ArrayList;

public class University {

    private ArrayList<StdNum> studentsNumbers = new ArrayList<StdNum>();

    public ArrayList<Faculty> faculties = new ArrayList<>();

    public boolean addStdNum( StdNum number )
    {
        if( searchStdNum( number ) == null  ) {
            System.out.println("null");
            studentsNumbers.add( number );
            return true;
        }
        return false;
    };

    public boolean deleteStdNum( StdNum number )
    {
        StdNum current = searchStdNum( number );
        if( current!=null )
        {
            studentsNumbers.remove( number );
            return true;
        }
        return false;
    };

    public StdNum searchStdNum( StdNum number )
    {
        for(int i=0;i<studentsNumbers.size();i++)
        {
            if( number.getStdNum().equals( studentsNumbers.get(i).getStdNum() ) ) return studentsNumbers.get(i);
        };

        return null;
    };

    public void addFaculty( Faculty faculty )
    {
        this.faculties.add( faculty );
    };

    public Faculty getFaculty( int index )
    {
        return this.faculties.get(index);
    };

}

