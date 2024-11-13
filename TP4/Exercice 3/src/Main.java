import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        //Inserting the Students
        Etudiant e1 = new Etudiant("Abdelkebir");
        Etudiant e2 = new Etudiant("doha");
        Etudiant e3 = new Etudiant("rachid");
        Etudiant e4 = new Etudiant("Abdelkebir");
        Etudiant e5 = new Etudiant("doha");
        Etudiant e6 = new Etudiant("rihana");

        //Creation of Groups
        HashSet<Etudiant> groupeA = new HashSet<Etudiant>();
        HashSet<Etudiant> groupeB = new HashSet<Etudiant>();

        //Adding the Students to the groups
        groupeA.add(e1);groupeA.add(e2);groupeA.add(e3);
        groupeB.add(e4);groupeB.add(e5);groupeB.add(e6);

        //Show the Students inside the groups
        System.out.println("Group A : ");
        groupeA.forEach((Etudiant e ) -> {
            System.out.println(e.getName());
        });

        System.out.println("-".repeat(1000));
        System.out.println("Group B : ");
        groupeB.forEach((Etudiant e ) -> {
            System.out.println(e.getName());
        });

        HashSet<Etudiant> intersection = new HashSet<>(groupeA);

        intersection.retainAll(groupeB);

        System.out.println("-".repeat(1000));
        System.out.println("The intersactions : ");
        intersection.forEach((Etudiant e ) -> {
            System.out.println(e.getName());
        });
        System.out.println("-".repeat(1000));
        System.out.println("-".repeat(1000));

        //Union of two Hashset
        groupeA.addAll(groupeB);

        System.out.println("This is the Union of the Hashes !");
        groupeA.forEach((Etudiant e) -> {
            System.out.println(e.getName());
        });
        System.out.println("-".repeat(1000));



    }

}