import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Notin boolen = new Notin();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter A string : ");
        char[] arr = scan.nextLine().toUpperCase().toCharArray();
        int[] tab_int = new int[26];
        char[] tab_str = new char[100];
        int index = 0;


        for (int i = 0 ; i < arr.length ; i++)
        {
            int count = 0;
                if (boolen.notonit(tab_str, arr[i]) == 0)
                {
                    for (int j = i; j < arr.length ; j++)
                    {
                        if (arr[i] == arr[j])
                            count++;
                    }
                    tab_str[index] = arr[i];
                    tab_int[index] = count;
                    index++;
                }
        }
        for (int i = 0; i < index; i++) {
            if (tab_str[i] != ' ')
                System.out.println(tab_int[i] + " fois lettre : " +tab_str[i]);
        }


        }

}
