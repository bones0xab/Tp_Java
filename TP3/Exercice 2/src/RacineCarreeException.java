public class RacineCarreeException extends Exception{
    public RacineCarreeException(int i)
    {
        super("C'est une exception de type RacineCarreeException Nombre négatif : " + i);
    }
}
