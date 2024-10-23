public class Notin {
    public int notonit(char[] str, char l)
    {
        for(int i = 0 ; i < str.length ; i++)
        {
            if (str[i] == l)
                return 1;
        }
        return 0;
    }
}
