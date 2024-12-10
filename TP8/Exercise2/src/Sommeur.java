public class Sommeur implements Runnable{
    private int[] array;
    private int start;
    private int end;
    private int somme;

    public Sommeur(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.somme = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            somme += array[i];
        }
    }

    public int getSomme() {
        return somme;
    }
}

//The run method finnally i all the understood in my mind , it s for just task , then in thread we made it repeadlty to make many tasks in the same way.

