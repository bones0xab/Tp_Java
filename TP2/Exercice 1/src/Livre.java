public class Livre {
    private long ISBN;
    private String titre;
    private Auteur aut;

    public Livre(Auteur new_aut, String titre, long ISBN)
    {
        this.aut = new_aut;
        this.ISBN = ISBN ;
        this.titre = titre;
    }

    public void afficher()
    {
        aut.afficher();
        System.out.println("This is ISBN :" + ISBN + " \nthis is title : " +titre);
    }
}
