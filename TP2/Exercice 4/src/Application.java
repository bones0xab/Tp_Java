import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] ars) {
        int choice = -1;
        long ids = 0;
        ImetierProduit interf = new MetierProduiImpl();
        Scanner scn = new Scanner(System.in);

        while (choice != 0) {
            System.out.println("1/ Afficher La liste des produits.");
            System.out.println("2/ Rechercher des produits par mot clé.");
            System.out.println("3/ Ajouter un nouveau produit dans la liste.");
            System.out.println("4/ Récuperer et afficher un produit par ID.");
            System.out.println("5/ Supprimer un produit par id.");
            System.out.println("6/ To quit tap random number .");


            System.out.print("Enter a choice: ");
            int choix = scn.nextInt();
            scn.nextLine();

            switch (choix) {
                case 1:
                    List<Produit> products = interf.getAll();
                    if (products.toArray().length == 0 )
                    {
                        System.out.println("List empty !");
                    }
                    else
                    {
                        for (Produit p : products) {
                            System.out.println(p.toString());
                            System.out.println("\n");
                        }
                    }

                    System.out.println("\n\n");
                    break;

                case 2:
                    System.out.print("Enter a Name : ");
                    String searched_str = scn.nextLine();
                    System.out.println("The Products searched: \n");
                    List<Produit> list_pro = interf.findByNom(searched_str);

                    if (list_pro.toArray().length == 0)
                    {
                        System.out.println("This is Not in list ! \n\n");
                    }
                    else {
                        for(Produit p : list_pro)
                        {
                            System.out.println(p.toString());
                            System.out.println("\n");
                        }
                        System.out.println("\n\n\n");
                    }
                    break;

                case 3:
                    Produit p = new Produit();
                    p.setId(ids);
                    ids++;

                    System.out.print("Enter Name: ");
                    p.setName(scn.nextLine());

                    System.out.print("Enter Marque: ");
                    p.setMarque(scn.nextLine());

                    System.out.print("Enter Description: ");
                    p.setDescription(scn.nextLine());

                    System.out.print("Enter Price: ");
                    p.setPrice(scn.nextDouble());

                    System.out.print("Number of Stock: ");
                    p.setStock(scn.nextLong());

                    interf.add(p);
                    System.out.println("\n\n\n");
                    break;

                case 4:
                    System.out.print("Enter Id: ");
                    long i = scn.nextLong();
                    if (interf.findById(i) == null)
                    {
                        continue;
                    }
                    else
                    {
                        System.out.println(interf.findById(i).toString());
                    }
                    System.out.println("\n\n\n");
                    break;

                case 5:
                    System.out.print("Enter Id: ");
                    long ii = scn.nextLong();
                    interf.delete(ii);
                    System.out.println("\n\n\n");
                    break;

                default:
                    choice = 0;
                    System.out.println("Quit program");
                    break;


            }
        }
        scn.close();
    }
}
