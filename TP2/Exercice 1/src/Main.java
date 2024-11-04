public class Main {
    public static void main(String[] args) {
        // Create an instance of Adherent
        Adherent adherent = new Adherent("Bouchti", "Abdelkebir", "abdo@gmail.com", 23, "065432191", 101);

        // Create an instance of Auteur
        Auteur auteur = new Auteur("Badaoui", "Houda", "houda@gmail.com", 45, "067834578", 202);

        // Create an instance of Livre with the Auteur instance
        Livre livre = new Livre(auteur, "Boite à Merveilles", 9781234567890L);

        // Display information about the Adherent
        System.out.println("Informations sur l'adhérent:");
        adherent.afficher();

        System.out.println("\nInformations sur le livre:");
        // Display information about the Livre and its Auteur
        livre.afficher();
    }
}
