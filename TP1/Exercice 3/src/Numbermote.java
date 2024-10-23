public class Numbermote {
    public int numberM(String str) {

        //In this line we remove first and last spaces then we split and count the words
        String[] words = str.trim().split(" +");
        return words.length;
    }
}