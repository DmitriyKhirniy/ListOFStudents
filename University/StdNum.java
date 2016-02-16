package University;

public class StdNum {
    private String num;

    public StdNum( String num )
    {
        this.num =  num ;
    };

    private static String checkNum( String num )
    {
        String check = new String( num.replace( "", "") );

        char[] numbers = {0,1,2,3,4,5,6,7,8,9};

        if( check.length() != 11 ) return null;
        else if( check.charAt(2)!='№' ) return null;
        else return num;
    };

    public String getStdNum()
    {
        return this.num;
    };

    public void setStdNum( String num )
    {
        this.num = checkNum( num );
    };
}
