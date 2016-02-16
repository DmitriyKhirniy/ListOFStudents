package University;
public class Names {
    private String firstName;
    private String middleName;
    private String lastName;

    public Names( String firstName , String middleName , String lastName )
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    };

    public Names( Names name )
    {
        this.firstName = this.checkNames( name.getFirstName(), "first name" );
        this.middleName = this.checkNames( name.getMiddleName(), "middle name" );
        this.lastName = this.checkNames( name.getLastName() , "last name");
    };
    public String toString()
    {
        return this.lastName+" "+this.firstName+" "+this.middleName;
    };

    public String getFirstName()
    {
        return this.firstName;
    };

    public String getMiddleName()
    {
        return this.middleName;
    };

    public String getLastName()
    {
        return this.lastName;
    };

    public void setFirstName( String firstName )
    {
        this.firstName = this.checkNames( firstName , "first name");
    };

    public void setMiddleName( String middleName )
    {
        this.middleName = this.checkNames( middleName , "middle name");
    };

    public void setLastName( String lastName )
    {
        this.lastName = this.checkNames( lastName , "last name");
    };

    private static String checkNames( String string , String what )
    {
        char[] xSymbols = {'0','1','2','3','4','5','6','7','8','9','*','&','?','!','@','$'};
        boolean contain = false;

        for( int i=0;i<string.length();i++ )
        {
            for(int j=0;j<xSymbols.length;j++)
            {
                if( string.charAt(i) == xSymbols[j] )
                {
                    contain = true;
                    break;
                };
            };
            if( contain ) break;
        };

        if( string.length() < 3 || string.length() > 25  )
        {
            System.out.println( "Undefined size of "+what );
            return "null";
        }
        else if( contain )
        {
            System.out.println( "Undefined element in "+what );
            return "null";
        }
        else return string;
    };
}

