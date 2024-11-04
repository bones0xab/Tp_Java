
public class Manager extends Employe {
    private String service;

    public Manager(String name,String prenom , String email,double salaire , String tel,String Service)
    {
        super(name,prenom,email,salaire,tel);
        this.service = Service;
    }

    public double calculerSalaire()
    {
     return getSalaire()*0.20;
    }
    public void afficher() {
        super.afficher();
        System.out.println("Salary : " + this.getSalaire()*1.20);
        System.out.println("Service : " + this.service);
    }

}
