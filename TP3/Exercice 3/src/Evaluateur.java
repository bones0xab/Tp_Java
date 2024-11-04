public class Evaluateur {
    public Evaluateur(){}

    public void verifierNote(int i) throws NoteInvalideException
    {
        if (i < 0 || i > 20)
            throw new NoteInvalideException(i);

    }

    public static void main(String[] args) {
        Evaluateur eval = new Evaluateur();

        try{
            eval.verifierNote(15);
            eval.verifierNote(25);
        }catch (NoteInvalideException e)
        {
            e.printStackTrace();
        }
    }
}
