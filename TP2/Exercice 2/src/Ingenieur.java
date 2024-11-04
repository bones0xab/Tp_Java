import jdk.nio.mapmode.ExtendedMapMode;

public class Ingenieur extends Employe {
    private String specialite;
    public Ingenieur(String name,String prenom , String email,double salaire , String tel, String spe)
    {
        super(name,prenom,email,salaire,tel);
        this.specialite = spe;
    }

    public double calculerSalaire()
    {

        return getSalaire()*0.15;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Salary  : "+this.getSalaire()*1.15);
        System.out.println("Service : " + this.specialite);
    }
}
