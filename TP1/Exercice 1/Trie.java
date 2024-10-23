import java.util.Arrays;

public class Trie {
    public double[] trienote(double[] notes){
        Arrays.sort(notes);

        //this is for showing the notes
        for(double note : notes){
            System.out.println(note);
        }
        return notes;
    }
}
