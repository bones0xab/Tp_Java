public class Calculateur {
    public void testRacineCarree(int i) throws RacineCarreeException
    {
        if (i < 0)
        {
            throw new RacineCarreeException(i);
        }
    }

    public static void main(String[] args) {
                Calculateur c = new Calculateur();
                try{
                    c.testRacineCarree(-5);
                    c.testRacineCarree(25);
                }catch (RacineCarreeException e)
                {
                    e.printStackTrace();
                }
    }
}

