public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Talkative(i));
            thread.start();
        }
    }

}

//The output show different instances of printed and mixed order talkative ,they are printed simultaneity, so we can conclude that when we execute the run in runnable interface to