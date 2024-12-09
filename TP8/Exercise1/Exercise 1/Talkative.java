class Talkative implements Runnable {
    private final int number;

    public Talkative(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Talkative instance " + number + " - Message " + i);

        }
    }
}

//Runnable is interface to made a task executed by thread.
//THread is to run the task concurrentlu at the same time
//in the conclusion we use thread to made separeate block of tasks and we use the start to execute or implement all in the same time
