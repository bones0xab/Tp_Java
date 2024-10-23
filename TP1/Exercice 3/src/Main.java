import java.sql.Struct;
import java.util.Enumeration;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //The instanciation of Classes
        Saisir s = new Saisir();
        Affiche a = new Affiche();
        Inverser inv = new Inverser();
        Numbermote number = new Numbermote();
        Scanner scan = new Scanner(System.in);

        //This is the String for working with it in all the code
        String Current_String = "";
        int choix = -1;

        //This loop is repeated Untill the user choose to exit
        while (choix != 0)
        {
            System.out.println("#----------------Menu----------------#");
            System.out.println(" 0/ Exit");
            System.out.println(" 1/ Insert String ");
            System.out.println(" 2/ Show String");
            System.out.println(" 3/ Reverse String");
            System.out.println(" 4/ Count Words");
            System.out.print("Choice  : ");
            choix = scan.nextInt();


            //All options
            switch (choix)
            {

                case 1 :
                    //Read and store the String
                    Current_String = s.Lire_stocke();
                    break;
                case 2 :
                    //This is For Showing The String
                    if(!Current_String.isEmpty())
                        a.affiche(Current_String);
                    break;
                case 3 :
                    if(!Current_String.isEmpty())
                        System.out.println("This is the reversed String  : " + inv.inverse(Current_String));
                    break;
                case 4 :
                    if(!Current_String.isEmpty())
                        System.out.println("This is the Number of words in the sentence : " + number.numberM(Current_String));
                    break;
                default :
                    System.out.println("You exit !");
                    break;
            }
            System.out.println("Press any key to continue...");
            //This is a try except block for reading a user keyboard also for clearing the buffer entred
            try {
                System.in.read();
                System.in.skip(System.in.available());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scan.close();
    }
}