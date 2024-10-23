public class affmoyenne {
    public void affiche_moyenne(double[] notes)
    {
        double moy = 0 ;
        for(double note: notes)
        {
            moy+=note;
        }
        moy /= notes.length;
        System.out.println("This is the moyenne Note " + moy);
    }
}

