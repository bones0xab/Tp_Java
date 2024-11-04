import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String nom , desciption ;
    private List<Ordinateur> objects_ordinateur;

    public Categorie(String n , String p)
    {
        this.nom = n;
        this.desciption = p;
        objects_ordinateur = new ArrayList<Ordinateur>();
    }

    public void ajouterOrdinateurs(Ordinateur ord)
    {
        if (!objects_ordinateur.contains(ord))
        {
            objects_ordinateur.add(ord);
        }
        else
            System.out.println("Already in the List !");
    }

    public void supprimerOrdinateur(Ordinateur ord)
    {
        if (objects_ordinateur.contains(ord))
            objects_ordinateur.remove(ord);
        else
            System.out.println("This is not on the list !");
    }

    public List<Ordinateur> rchercheParPrix(double price)
    {
        List<Ordinateur> ord_price = new ArrayList<Ordinateur>();

        for (Ordinateur o : objects_ordinateur)
        {
            if (o.getPrix() == price)
                ord_price.add(o);
        }
        if(ord_price.isEmpty())
        {
            System.out.println("No Ordinator has this price !");
            return new ArrayList<>();
        }
        else
            return ord_price;
    }

    public String toString()
    {
        return "Categorie : \n " +
                "Nom : " + nom + "\n"
                + " Description : " + desciption ;
    }
}
