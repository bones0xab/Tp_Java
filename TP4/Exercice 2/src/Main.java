import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Double> notes = new HashMap<String, Double>();

        // Inserting the notes in the hashmap
        notes.put("Abdelkebir", 15.0);
        notes.put("Doha", 20.0);
        notes.put("Rachid", 18.8);
        notes.put("saif", 20.0);
        notes.put("Omar", 11.8);


        //Increasing Abdelkebir's Note
        notes.put("Abdelkebir", notes.get("Abdelkebir") + 3);

        System.out.println("*".repeat(10000));
        //Show the Size of Map
        System.out.println("This is the size of Map: " + notes.size());
        System.out.println("*".repeat(10000));

        System.out.println("\n");

        System.out.println("*".repeat(10000));
        //Remove Omar's Note
        System.out.println("This is the removed Note: " + notes.get("Omar"));
        notes.remove("Omar");
        System.out.println("*".repeat(10000));
        System.out.println("\n");

        //Use Collections for facilate the process
        Set<String> keys = notes.keySet();
        Collection<Double> values = notes.values();


        //Calculate the Average, Max, Min
        double median = 0;
        if (!notes.isEmpty()) {
            for (Double d : values) {
                median += d;
            }
        } else {
            System.out.println("Is empty!");
        }

        double maximum = Collections.max(values);
        double minimum = Collections.min(values);


        // Show the values of the Max, Average, Min
        System.out.println("*".repeat(10000));
        System.out.printf("La Moyenne: %.2f%n", median / notes.size());
        System.out.printf("This is the Max: %.2f%n", maximum);
        System.out.printf("This is the Min: %.2f%n", minimum);
        System.out.println("*".repeat(10000));
        System.out.println("\n");
        System.out.println("*".repeat(10000));
        // This is the Notes of Students who has 20 Note
        notes.forEach((key, value) -> {
            if (value == 20.0)
                System.out.println("This is a Student with the Note 20: " + key + "\n");
        });
        System.out.println("*".repeat(10000));
        System.out.println("\n");

        System.out.println("*".repeat(10000));
        // Displaying list of students and their notes with optimized output
        System.out.println("This is the list of Students with Notes! \n");
        notes.forEach((key, value) -> {
            System.out.printf("Student: %-10s --> Note: %.2f%n", key, value);
            System.out.println();
        });
        System.out.println("*".repeat(10000));
    }
}
