![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.001.png)

<a name="_top"></a>Practical Activity 3:

*Exception Handling*

![The State Of Java Development In 2023](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.002.jpeg)

*Realized By:  Abdelkebir Bouchti*



Content

**Exercise 01**

<a name="_1.1_class_diagram:"></a>[1.1 Class diagram](#page3)

[1.2 Class TropViteException](#page4)

[1.3 Class Vehicule ](#vehicule)

[](#vehicule)[1.4 Results ](#page5result)

**Exercise 02**

[2.1 Class diagram](#diagram2)

[2.2 Class RacineCarreeException](#racinecarreeexception)

[2.3 Class Calculateur ](#calculateur)

[2.4 Results](#result2)

**Exercise 03**

[3.1 Class diagram](#diagram3)

[3.2 Class NoteInvalideException ](#noteinvalideexception)

[3.3 Class Evaluateur](#evaluateur)

[3.4 Results](#results3)


[**Summary](#summary)** 






Exercise 1:

In this exercise we want to learn how to handle the exceptions of speed, the purpose here is to made the coding simple,

In order to save time and made an awesome debugging and track during the code.

1. <a name="page3"></a>Class diagram

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.003.png)

Figure 1 – Show the class diagram of the exercise.



1. <a name="page4"></a>TropViteException Class

   Tasks: 

- Create the class TropViteException that inheritance from Exception class.
- Create a cunstrutor who have int as an input.
- Made a call to super class with a message given plus int. 

public class TropViteException extends  Exception{

`    `public TropViteException(int i)
`    `{
`        `super("C'est une exception de type TropViteException Vitesse en cause : " + i);
`    `}

}

1. <a name="vehicule"></a>Vehicule Class

`     `Tasks:

- Create the class Vehicule that propose a constructor empty.
- Create method testVitesse() who take as an input int.
- Create a main test and handle the exception using the try bloc.

public class Vehicule {
`    `public Vehicule() {

`    `}

`    `//We made these throws to test exceptions
`    `public void testVitesse(int i) throws TropViteException {
`        `if (i > 90) {
`            `throw new TropViteException(i);
`        `}

`    `}

`    `public static void main(String[] args) {
`        `Vehicule v = new Vehicule();

`        `try {
`            `v.testVitesse(80);  // Should not throw an exception
`            `v.testVitesse(100); // This will throw TropViteException
`        `} catch (TropViteException e) {
`            `// Handle the exception
`           `e.printStackTrace();
`        `}
`    `}
}


<a name="page5result"></a>1.4 Results:

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.004.png)














Exercise 2: 

The purpose of this exercise is like the previous one, just this point on the case of exceptions and how to handle it, in here we want to test the case of square negative.

<a name="diagram2"></a> 2.1 Class Diagram 

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.005.png)

Figure 2: Class diagram of the exercise.

<a name="racinecarreeexception"></a>2.2  RacineCarreeException Class.

Tasks : 

- Create and inheritance from the class Exception.
- Create a constructor who doesn’t have return and give him an int as an input.
- Handle the case of exception.

public class RacineCarreeException extends Exception{
`    `public RacineCarreeException(int i)
`    `{
`        `super("C'est une exception de type RacineCarreeException Nombre négatif : " + i);
`    `}
}

<a name="calculateur"></a>2.3 Calculateur Class.

Tasks : 

- Create a class calculateur without attribute.
- Create a constructor who can take an int as input.
- Create a main method with tests.

public class Calculateur {
`    `public void testRacineCarree(int i) throws RacineCarreeException
`    `{
`        `if (i < 0)
`        `{
`            `throw new RacineCarreeException(i);
`        `}
`    `}

`    `public static void main(String[] args) {
`                `Calculateur c = new Calculateur();
`                `try{
`                    `c.testRacineCarree(-5);
`                    `c.testRacineCarree(25);
`                `}catch (RacineCarreeException e)
`                `{
`                    `e.printStackTrace();
`                `}
`    `}
}

<a name="result2"></a>2.4 Results.

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.006.png)


Exercise 03: 

The purpose of this exercise is the same as the previous exercises, just they differentiate in the purpose , like this one , the purpose here is to  validate the note between 0 and 20, using the same method and way in the previous exercises.

<a name="diagram3"></a>3.1 Class diagram

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.007.png)

Figure 3 : Diagram of class .

3\.2 <a name="noteinvalideexception"></a>NoteInvalideException Class.

Tasks : 

\- Create a class NoteincalideException and inheritance the Exception class.

\- Create a constructor for taking an int as input, and handle the cases of exceptions.

\- Give the String and the int to the super class Exception.

public class NoteInvalideException extends Exception{
`    `public NoteInvalideException(int i)
`    `{
`        `super("Exception de type NoteInvalideException invalide : " + i);
`    `}
}

3\.3 <a name="evaluateur"></a>Evaluateur Class.

Tasks : 

- Create the class Evaluateur without attributes.
- Create an empty constructor.
- Create a method can take int as an input,  and handle the case exception between 0 and 20.
- Create Main method to test .

public class Evaluateur {
`    `public Evaluateur(){}

`    `public void verifierNote(int i) throws NoteInvalideException
`    `{
`        `if (i < 0 || i > 20)
`            `throw new NoteInvalideException(i);

`    `}

`    `public static void main(String[] args) {
`        `Evaluateur eval = new Evaluateur();

`        `try{
`            `eval.verifierNote(15);
`            `eval.verifierNote(25);
`        `}catch (NoteInvalideException e)
`        `{
`            `e.printStackTrace();
`        `}
`    `}
}

3\.4 Resu<a name="results3"></a>lts.

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.008.png)




<a name="summary"></a>Summary 

In the end, we can finish with this conclusion, first we saw how to use the exceptions for handling specific cases, and for multiples ways and errors, We work with the class Exception who has the power to show some traces and messages in the output program, it’s very useful when we want to debug a big applications or projects.

![](Aspose.Words.8dace732-7011-4d5d-8d52-6e92a187a4e2.009.jpeg)


