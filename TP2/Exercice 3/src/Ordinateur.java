public class Ordinateur {
    private String nom, marque, description;
    private long stock;
    private double prix;


    //This is a instructor
    public Ordinateur(String n , String m , String d, long s, double p)
    {
        this.nom = n;
        this.marque = m;
        this.description = d;
        this.stock = s;
        this.prix = p;

    }

    public double calcule_prix(long quentite)
    {
            return prix*quentite;
    }
    public double getPrix() {
        return this.prix;
    }
    public String getName()
    {
        return this.nom;
    }
    public String getMarque()
    {
        return this.marque;
    }
    public String getDescription()
    {
        return this.description;
    }


    public String toString()
    {
        return "Name : " + nom + "\n" +
                "Marque : " + marque + "\n" +
                "Description : " + description + "\n" +
                "Price : " + prix;
    }

}
