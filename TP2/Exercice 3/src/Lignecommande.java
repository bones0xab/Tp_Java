public class Lignecommande {
    private long quntite;
    private Commande cmd;
    private Ordinateur ord;

    public Lignecommande(long q, Commande c , Ordinateur o)
    {
        this.quntite = q;
        this.cmd = c;
        this.ord = o;
    }

    public String ToString()
    {
        return "Quantite : " + quntite + "\n" +
                "Command : " + cmd + "\n" +
                "Ordinateur : " + ord + "\n";
    }

    public Commande getCommand()
    {
        return this.cmd;
    }
    public Ordinateur getOrdinateur()
    {
        return this.ord;
    }
    public long getQuantity()
    {
        return this.quntite;
    }


}
