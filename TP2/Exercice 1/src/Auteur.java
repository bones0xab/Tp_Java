public class Auteur extends Personne{

    public int numAuteur ;

    public Auteur(String nom,String prenom,String email,long age, String tel,int numAuteur)
    {
        super(nom,prenom,email,age,tel);
        this.numAuteur = numAuteur;
    }

    public void afficher()
    {
        super.afficher();
        System.out.println("This is number of Autor : " + numAuteur);
    }

}
