import java.io.LineNumberInputStream;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        ArrayList<Employe> list = new ArrayList<Employe>();

        Employe e1 = new Employe("Abdelkebir","Bouchti",7000);
        Employe e2 = new Employe("Doha","Banoui",8000);
        Employe e3 = new Employe("Rachid","Hsina",7000);
        Employe e4 = new Employe("Abdesamad","zelzouli",20000);

        list.add(e1);list.add(e2);list.add(e3);list.add(e4);


        System.out.println("-".repeat(1000));
        double totalsalary = list.stream()
                .mapToDouble(e -> e.getSalaire())
                .sum();

        System.out.println("Total Salary : " + totalsalary);
        System.out.println("-".repeat(1000));


        System.out.println("-".repeat(1000));
        list.stream()
                .map(Employe::getName)
                .sorted()
                .forEach(System.out::println);
        System.out.println("-".repeat(1000));


        System.out.println("-".repeat(1000));
        double MinSalary = list.stream()
                .mapToDouble(Employe::getSalaire)
                .min()
                .orElse(0.0);
        if (MinSalary != 0)
            System.out.println("Min Salary : " + MinSalary);
        else
            System.out.println("This is an empty list ! \n");
        System.out.println("-".repeat(1000));


        System.out.println("-".repeat(1000));

        System.out.println("Enter Salary for filtring the Employes :");
        Scanner scn = new Scanner(System.in);

        double Compare = scn.nextDouble();
        ArrayList<Employe> new_list =
                       list.stream()
                    .filter(e -> e.getSalaire() > Compare)
                               .collect(Collectors.toCollection(ArrayList::new));

        if(!new_list.isEmpty())
        {
            for (Employe e : new_list)
            {
                System.out.println(e.getName());
            }
        }
        else
            System.out.println("No one has Greater then this value : "+ Compare);

        scn.close();
        System.out.println("-".repeat(1000));
        System.out.println("-".repeat(1000));

        //here converting employe object to it s salary
        double maxSalary =
                list.stream()
                        .map(Employe::getSalaire)
                        .reduce(Double.MIN_VALUE, (e,ee) -> e > ee ? e : ee  );

        if (maxSalary != 0)
            System.out.println("The max salary : " + maxSalary);
        else
            System.out.println("This is Empty list !");


        System.out.println("-".repeat(1000));

        String concatenateNames = list.stream()
            .map(Employe::getName)
            .reduce("", (n1,n2) -> n1 + n2);


        System.out.println("Concatenate : " + concatenateNames);
        System.out.println("-".repeat(1000));

        }
}