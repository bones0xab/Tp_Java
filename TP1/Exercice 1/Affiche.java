public class Affiche {
    public void afficheNbr(double[] notes, double note)
    {
        int i = 0;
        for(double n : notes)
        {
            if (n == note)
                i++;
        }
        if (i != 0)
            System.out.println("This is the number of students had got : " + note + " is " + i + " Strudents");
        else
            System.out.println("There are no students has this ");
    }
}
