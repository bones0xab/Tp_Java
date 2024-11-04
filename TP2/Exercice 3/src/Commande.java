import java.util.random.RandomGenerator;


public class Commande {
    private String reference, date ,etat_command;
    private Client client;


    public Commande(String ref , Client c, String date_commande, String etat )
    {
        this.reference = ref;
        this.client = c;
        this.date = date_commande;
        this.etat_command = etat;
    }

    public String getRef()
    {
        return this.reference;
    }
    public String getDate()
    {
        return this.date;
    }
    public String getEtat()
    {
        return this.etat_command;
    }
    public Client getClient()
    {
        return this.client;
    }



    public String toString()
    {
        return "Reference : " + reference + "\n" +
                "\nInformation about Client : " + client + "\n" +
                "DateCommande : " + date + "\n" +
                "Etat : " + etat_command ;
    }
}

