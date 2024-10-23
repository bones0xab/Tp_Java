import java.lang.reflect.Array;
import java.util.Arrays;

public class maxmin {
    public void max_min(double[] notes)
    {
        Arrays.sort(notes);
        System.out.println("This is the Min : "+notes[0]);
        System.out.println("This is the Max : "+notes[notes.length -1]);
    }
}


