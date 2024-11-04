import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creating a client
        Client client = new Client("John", "Doe", "123 Main St", "john.doe@example.com", "Paris", "0123456789");

        // Creating a category
        Categorie categorie = new Categorie("Laptops", "High-performance laptops");

        // Creating some computers
        Ordinateur laptop1 = new Ordinateur("XPS 13", "Dell", "High-end ultraportable", 10, 1200.00);
        Ordinateur laptop2 = new Ordinateur("MacBook Pro", "Apple", "Professional laptop", 5, 2500.00);
        Ordinateur laptop3 = new Ordinateur("ThinkPad X1", "Lenovo", "Business laptop", 8, 1800.00);

        // Adding computers to the category
        categorie.ajouterOrdinateurs(laptop1);
        categorie.ajouterOrdinateurs(laptop2);
        categorie.ajouterOrdinateurs(laptop3);

        // Displaying category details
        System.out.println(categorie);

        // Searching for a computer by price in the category
        System.out.println("\nSearching for laptops priced at 1800.00:");
        List<Ordinateur> foundOrdinateurs = categorie.rchercheParPrix(1800.00);
        for (Ordinateur ord : foundOrdinateurs) {
            System.out.println(ord);
        }

        // Creating a command for the client
        Commande commande = new Commande("CMD123", client, "2024-11-03", "Pending");
        client.Ajouter_command(commande);

        // Creating a line command (order line) for the command
        Lignecommande ligneCommande = new Lignecommande(2, commande, laptop1);

        // Displaying order details
        System.out.println("\nOrder Details:");
        System.out.println(commande);

        // Displaying line command details
        System.out.println("\nLine Command Details:");
        System.out.println(ligneCommande);

        // Calculating and displaying the price for the ordered quantity
        double totalCost = laptop1.calcule_prix(ligneCommande.getQuantity());
        System.out.println("\nTotal Cost for " + ligneCommande.getQuantity() + " units of " + laptop1.getName() + ": $" + totalCost);
    }
}
