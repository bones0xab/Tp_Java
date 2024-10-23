import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //To insert the number of notes
        Scanner scan = new Scanner(System.in);
        System.out.print("How many Notes to Inter ? : ");
        int number_to_insert = scan.nextInt();

        //This is a table of notes
        double[] notes = new double[number_to_insert];

        //Loop for inserting every note
        for(int i = 0 ; i < number_to_insert ; i++)
        {
            System.out.print("Enter Note : ");
            notes[i] = scan.nextDouble();
        }

        //Instanciation of Class
        Trie t = new Trie();
        affmoyenne m = new affmoyenne();
        maxmin m_m = new maxmin();
        Affiche affiche = new Affiche();

        System.out.println("Sorted note : ");
        double[] sortednotes = t.trienote(notes);

        m.affiche_moyenne(notes);
        m_m.max_min(notes);
        System.out.print("Enter a Note to see how many students has it : ");
        affiche.afficheNbr(notes,scan.nextDouble());
        scan.close();
    }
}