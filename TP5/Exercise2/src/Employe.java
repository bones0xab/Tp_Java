public class Employe {

    private String Nom,Departement;
    private double salaire;

    public Employe(String n, String d, double s)
    {
            this.Nom = n;
            this.Departement = d;
            this.salaire = s;
    }

    public String getName()
    {
        return this.Nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public String getDepartement() {
        return Departement;
    }
}
