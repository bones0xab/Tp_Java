import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        substract s = new substract();
        System.out.print("Enter A verb : ");
        Scanner scan = new Scanner(System.in);
        String final_S = s.sub(scan.nextLine());

        System.out.println("je "+final_S+"e");
        System.out.println("tu "+final_S+"es");
        System.out.println("il "+final_S+"e");
        System.out.println("nous "+final_S+"ons");
        System.out.println("vous "+final_S+"ez");
        System.out.println("ils "+final_S+"ent");
    }
}