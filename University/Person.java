package University;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Person {

    private Names name;

    private GregorianCalendar birthDate;

    private int age;

    private int personId;

    public Person(Names name, GregorianCalendar birthDate)
    {
        this.setNames( name );
        this.birthDate= birthDate;
        this.age = calculateAge( birthDate.getTime() );
        personId = this.hashCode();
    };

    public int getPersonId()
    {
        return this.personId;
    };

    public GregorianCalendar getDate()
    {
        return this.birthDate;
    };

    public void setDate( GregorianCalendar date )
    {
        this.birthDate = date;
    };


    public Names getName()
    {
        return this.name;
    };

    public void setNames( Names _name ){

        this.name = new Names( _name );

    };
    public void setName( String name )
    {
        //StringTokenizer str = new StringTokenizer(name);
        // System.out.println( str.nextToken() +"|"+ str.nextToken()+"|"+str.nextToken()  );
        //this.name = new Names( str.nextToken() , str.nextToken(), str.nextToken() );
    };

    public void setAge( int age )
    {
        this.age = age;
    };

    public int getAge()
    {
        return this.age;
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
        return age;
    }
}
