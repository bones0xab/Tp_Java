import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom, prenom,addres, email , ville,tel;
    private List<Commande> list_command;

    public Client(String n , String p , String a , String e , String v , String t)
    {
        this.nom = n;
        this.prenom = p;
        this.addres = a;
        this.email = e;
        this.ville = v;
        this.tel = t;
        this.list_command = new ArrayList<Commande>();
    }

    public void Ajouter_command(Commande cmd)
    {
        if(!list_command.contains(cmd))
            list_command.add(cmd);
        else
            System.out.println("Already in list !");
    }

    public void Supprimer_list(Commande cmd)
    {
        if(list_command.contains(cmd))
            list_command.remove(cmd);
        else
            System.out.println("Not in the List !");
    }

    public String getName()
    {
        return this.nom;
    }
    public String getSname()
    {
        return this.prenom;
    }
    public String getAddress()
    {
        return this.addres;
    }
    public String getTel()
    {
        return this.tel;
    }

    public List<Commande> getListCommand()
    {
        return this.list_command;
    }


    public String toString()
    {
        return " \nNom : " + nom + "\n" +
                "Prenom : " + prenom + "\n" +
                "Address : " + addres + "\n" +
                "Email : " + email + "\n" +
                "Ville : " + ville + "\n" +
                "Tel : " + tel ;
    }
}
