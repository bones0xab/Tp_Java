import java.lang.reflect.Array;

public class Inverser {
    public String inverse(String str)
    {
        StringBuilder n_str = new StringBuilder(str);
        return n_str.reverse().toString();
    }
}
