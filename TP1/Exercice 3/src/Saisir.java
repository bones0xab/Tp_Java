import java.util.Scanner;

public class Saisir {
    public String Lire_stocke()
    {
        System.out.print("Insert a String : ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
