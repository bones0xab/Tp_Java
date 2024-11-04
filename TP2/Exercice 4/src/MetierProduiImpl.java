import java.util.ArrayList;
import java.util.List;

public  class MetierProduiImpl implements ImetierProduit{
    List<Produit> produits = new ArrayList<Produit>();

    public Produit add(Produit p)
    {
        if (!produits.contains(p))
            produits.add(p);
        else
            System.out.println("Already in the List !");
        return p;
    }

    public List<Produit> getAll()
    {
        return produits;
    }

    public List<Produit> findByNom(String motCle)
    {
        List<Produit> list_produits = new ArrayList<Produit>();
        for(Produit p : produits)
        {
            if (p.getName().equals(motCle))
                list_produits.add(p);
        }
        return list_produits;
    }

    public Produit findById(long id)
    {

        for(Produit p : produits)
        {
            if(p.getId() == id)
                return p;
        }
        System.out.println("Not Exist this Id !");
        return null;
    }

    public void delete(long id)
    {
        for(Produit p : produits)
        {
            if(p.getId() == id)
            {
                System.out.println("Deleted Product : " + p.getName() + "\n");
                produits.remove(p);
                break;
            }
        }
    }
}
