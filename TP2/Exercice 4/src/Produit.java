import java.util.SimpleTimeZone;

public class Produit {
    private long Id;
    private String Nom, Marque, Description;
    private double prix;
    private long stock;


    public void setId(long id)
    {
        this.Id = id;
    }
    public void setName(String n)
    {
        this.Nom= n;
    }
    public void setMarque(String m)
    {
        this.Marque = m;
    }
    public void setDescription(String d)
    {
        this.Description= d;
    }
    public void setPrice(double p)
    {
        this.prix= p;
    }
    public void setStock(long l)
    {
        this.stock= l;
    }




    public long getId()
    {
        return this.Id;
    }

    public String getName()
    {
        return this.Nom;
    }
    public String getMarque(){
        return this.Marque;
    }

    public String toString()
    {
        return "Name : " + this.Nom + "\n" +
                "Marque : " + this.Marque + "\n" +
                "Description : " + this.Description + "\n" +
                "Price : " + this.prix;
    }

}
