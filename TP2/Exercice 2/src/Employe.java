public abstract class Employe {
    private String nom , prenom , email ;
    private double salaire;
    private String tel;

    public Employe(String name,String prenom , String email,double salaire , String tel)
    {
        this.nom = name;
        this.prenom = prenom;
        this.email = email;
        this.salaire = salaire;
        this.tel = tel;
    }

    public abstract double calculerSalaire();

    public double getSalaire()
    {
        return this.salaire;
    }
    public void afficher()
    {
        System.out.println("Name : " + this.nom);
        System.out.println("Second Name : " + this.prenom);
    }
}
