package University;

public class Cource {
    private String cource;

    public Cource( String cource )
    {
        this.cource = checkCource( cource );
    };

    private String checkCource( String cource )
    {
        String[] cources = {"I", "II" , "III", "IV", "V", "VI"};

        for(int i=0;i<cources.length;i++)
        {
            if(  cource.equals( cources[i] ) ) {
                return cource;
            }

        };

        switch( cource ){
            case "1": return "I";
            case "2": return "II";
            case "3": return "III";
            case "4": return "IV";
            case "5": return "V";
            case "6": return "VI";
            default: return null;
        }
    };

    public String getCource()
    {
        return this.cource;
    };

    public int getNumCource()
    {
        switch( cource ){
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            default: return 0;
        }
    };
}

