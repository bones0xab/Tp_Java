# TP8
---

Welcom

## Exercise 1 :fire:.
---

`Overview :` In this exercise we want to create a simple application or program for 
implementing the execution of the multiple threads,
and see how we can run blocks of code in parallel way (or in the same time).


**Tasks**
---

```bash
-> Create Classe Talkative with a constractor for inserting int number attribute.
-> Implemente the Runnable class to execute a task using the run method.
-> Execute a task using run method for showing the int with 100 time(we see after the exxecution of this for better understanding).
-> Create 10 instances in the main to test Thread execution.
-> Conclusion.
```

---


## Let's Code :start:
---

### Class Talkative
---
In this code I Implement the Runnable and as you can see the private attribute number to test with it the purpose of the exercise.


```java

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
```

### Main
---

```java
public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Talkative(i));
            thread.start();
        }
    }

}

//The output show different instances of printed and mixed order talkative ,they are printed simultaneity, so we can conclude that when we execute the run in runnable interface to

```


**After Running !**

It's a long output, don't worry i will explain for not overwealming or misunderstood.

```bash
In the output we will see each number from 1 to 10 and is repeated 100 time, but in ,arbetrery order,
This is the purpose of the exercise i just describe what i made !
```

This is the picture of the Execution !.



