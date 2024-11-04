public class Main {
    public static void main(String[] args)
    {
        // Creating instances of Ingenieur and Manager
        Ingenieur ingenieur = new Ingenieur("John", "Doe", "john.doe@example.com", 5000, "123456789", "Software");
        Manager manager = new Manager("Jane", "Smith", "jane.smith@example.com", 6000, "987654321", "HR");

        // Displaying details and calculated salaries for both
        System.out.println("Ingenieur Details:");
        ingenieur.afficher();
        System.out.println("Calculated Salary: " + ingenieur.calculerSalaire());

        System.out.println("\nManager Details:");
        manager.afficher();
        System.out.println("Calculated Salary: " + manager.calculerSalaire());
    }
}
