public class Adherent extends Personne{

    public int numAdherent ;
    public Adherent(String nom,String prenom,String email,long age, String tel, int numAdherent)
    {
        super(nom,prenom,email,age,tel);
        this.numAdherent = numAdherent;
    }

    public void afficher()
    {
        super.afficher();
        System.out.println("This is NumAdherent : " + numAdherent);
    }
}
