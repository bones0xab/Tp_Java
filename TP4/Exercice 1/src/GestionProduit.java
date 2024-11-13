import java.util.ArrayList;
import java.util.Scanner;

public class GestionProduit {

    // List to store products
    public ArrayList<Produit> produits = new ArrayList<>();

    // Method to add a product to the list
    public void AjouterProduit(Produit p) {
        if (produits.contains(p)) {
            System.out.println("This product is already in the list!");
        } else {
            produits.add(p);
        }
    }

    // Method to remove a product by its index
    public void supprimeProduit(int index) {
        if (index >= 0 && index < produits.size()) {
            Produit pro = produits.remove(index);
            System.out.println("The Removed Product: " + pro.getName());
        } else {
            System.out.println("Invalid index!");
        }
    }

    // Method to display all products in the list
    public void afficheProduits() {
        if (produits.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            for (Produit p : produits) {
                System.out.println("Name: " + p.getName() + "\n" +
                        "Price: " + p.getPrix() + " DH\n");
            }
        }
    }

    // Method to modify an existing product by index
    public void modifieProduit(int index) {
        if (index >= 0 && index < produits.size()) {
            Scanner sc = new Scanner(System.in);
            Produit p = produits.get(index);

            System.out.println("Enter the New Modification info: ");
            System.out.print("Name: ");
            p.setName(sc.next());
            System.out.print("Price: ");
            p.setPrix(sc.nextDouble());
        } else {
            System.out.println("Invalid index!");
        }
    }

    // Method to search for a product by its name
    public void rechercheNome(String n) {
        for (Produit p : produits) {
            if (p.getName().equals(n)) {
                System.out.println("Product Found:\n" +
                        "Name: " + p.getName() + "\n" +
                        "Price: " + p.getPrix() + "\n");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        GestionProduit G = new GestionProduit();

        // Creating sample products
        Produit p1 = new Produit(0, "Laptop", 39000);
        Produit p2 = new Produit(1, "Tablette", 45000);
        Produit p3 = new Produit(2, "Phone", 6000);

        // Adding products to the list
        G.AjouterProduit(p1);
        G.AjouterProduit(p2);
        G.AjouterProduit(p3);
        System.out.println("-".repeat(1000));

        System.out.println("This is the list of products:");
        G.afficheProduits();

        System.out.println("-".repeat(1000));


        // Modifying the first product
        System.out.println("Modifie the First Product with index  0 ! \n");
        G.modifieProduit(0);

        System.out.println("-".repeat(1000));

        System.out.println("\nUpdated list of products:");
        G.afficheProduits();

        // Removing a product by index
        G.supprimeProduit(1);

        System.out.println("-".repeat(1000));

        // Searching for a product by name
        System.out.println("\nSearching for 'Phone':");
        G.rechercheNome("Phone");

        System.out.println("-".repeat(1000));
        // Displaying final list of products
        System.out.println("Final List : ");
        G.afficheProduits();
        System.out.println("-".repeat(1000));

    }
}
