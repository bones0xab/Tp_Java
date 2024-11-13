import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Alc","Abdelkebir","Doha","Ikram","Rachid");

        System.out.println("This is list of words with 'a' character : \n");
        //The stream here is worked by providing a certain condition.
        list.stream()
                .filter(Name -> Name.contains("a"))
                .forEach(System.out::println);

        System.out.println("-".repeat(1000));

        System.out.println("This is The words reversed who are larged then 3 : \n");
        list.stream()
                .filter(name -> name.length() > 3)
                .map(name -> new StringBuilder(name).reverse().toString())
                .forEach(System.out::println);

        System.out.println("-".repeat(1000));
        System.out.println("This is the splited words with C character : \n");
        list.stream()
                .filter(name -> name.contains("c"))
                .map(name -> Arrays.asList(name.split("")))
                .forEach(System.out::println);

        System.out.println("-".repeat(1000));
        System.out.println("These are the words to Uppercase : \n");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("-".repeat(1000));
        System.out.println("These are the length of words : \n");
        list.stream()
                .map(String::length)
                .forEach(System.out::println);

        System.out.println("-".repeat(1000));
        System.out.println("This are the list of splited words : \n");
        List<String> result = list.stream()
                .flatMap(name -> Arrays.stream(name.split("")))
                .collect(Collectors.toList());

        System.out.println(result);

        System.out.println("-".repeat(1000));

        System.out.println("This is the index and word : \n");

        IntStream.range(0, list.size())
                        .mapToObj(i -> list.get(i) + "-" + i)
                        .forEach(System.out::println);
    }
}