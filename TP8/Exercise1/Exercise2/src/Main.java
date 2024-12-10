
import java.util.Arrays;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create an array of integers
        int[] array = new int[100];
        Arrays.fill(array, 21);
        // Define the number of threads
        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Calculate range per thread
        int range = array.length / numThreads;
        Sommeur[] sommeurs = new Sommeur[numThreads];
        Future<?>[] futures = new Future<?>[numThreads];

        // Assign tasks to threads
        for (int i = 0; i < numThreads; i++) {
            int start = i * range;
            int end = (i == numThreads - 1) ? array.length : start + range;
            sommeurs[i] = new Sommeur(array, start, end);
            futures[i] = executor.submit(sommeurs[i]);
        }

        // Wait for all threads to finish
        for (Future<?> future : futures) {
            future.get(); // Ensures the thread has finished
        }

        // Compute the total sum
        int totalSum = 0;
        for (Sommeur sommeur : sommeurs)
        {
            totalSum += sommeur.getSomme();
        }

        // Shutdown the executor
        executor.shutdown();

        // Print the result
        System.out.println("Total sum of array: " + totalSum);
    }
}