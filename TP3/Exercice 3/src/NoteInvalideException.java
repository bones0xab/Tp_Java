public class NoteInvalideException extends Exception{
    public NoteInvalideException(int i)
    {
        super("Exception de type NoteInvalideException invalide : " + i);
    }
}
