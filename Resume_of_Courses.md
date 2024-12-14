# Course 1 : Exceptions handling. 
---

Here is the updated summary of the document with **examples** included:

---

### **General Concepts of Exceptions**
- An **exception** is an unexpected event during program execution that disrupts the normal flow of instructions.
- **Difference between an error and an exception:**
  - **Exceptions**: Can be caught and handled by the programmer. Example: `IOException`.
  - **Errors**: Severe issues related to the JVM that cannot be handled programmatically. Example: `OutOfMemoryError`.

---

### **Exception Class Hierarchy**
- Root class: `Throwable` (a subclass of `Object`).
  - **Error**: Represents critical JVM issues. Example: `StackOverflowError`.
  - **Exception**: Represents recoverable issues.
    - **RuntimeException**: A subclass of `Exception` for unchecked exceptions, such as:
      - `ArithmeticException` (e.g., division by zero).
      - `NullPointerException` (e.g., accessing a null object).

---

### **Types of Exceptions**
1. **Checked (Surveillées):**
   - Verified at compile-time.
   - Must be handled using `try-catch` or declared with `throws`.
   - Examples:
     - `FileNotFoundException` when trying to open a non-existent file:
       ```java
       FileReader reader = new FileReader("file.txt"); // Throws FileNotFoundException
       ```
     - `IOException` during file reading:
       ```java
       BufferedReader br = new BufferedReader(reader);
       br.readLine(); // Throws IOException
       ```
2. **Unchecked (Non-surveillées):**
   - Not verified at compile-time, occurs during runtime.
   - Examples:
     - `ArithmeticException` (e.g., division by zero):
       ```java
       int result = 10 / 0; // Throws ArithmeticException
       ```
     - `ArrayIndexOutOfBoundsException`:
       ```java
       int[] arr = {1, 2};
       System.out.println(arr[5]); // Throws ArrayIndexOutOfBoundsException
       ```

---

### **Handling Exceptions**
- **Using `try-catch` blocks:**
  - `try`: Contains code that might throw an exception.
  - `catch`: Handles the exception.
  - `finally`: Executes code regardless of exceptions.
- Example:
  ```java
  try {
      int result = 10 / 0;
  } catch (ArithmeticException e) {
      System.out.println("Exception caught: " + e.getMessage());
  } finally {
      System.out.println("Execution continues.");
  }
  ```
  Output:
  ```
  Exception caught: / by zero
  Execution continues.
  ```

---

### **Propagating Exceptions**
- **Using `throw`:** Explicitly throws an exception.
- **Using `throws`:** Declares potential exceptions in a method signature.
- Example:
  ```java
  void divide(int a, int b) throws ArithmeticException {
      if (b == 0) {
          throw new ArithmeticException("Division by zero");
      }
      System.out.println(a / b);
  }
  ```
  - Calling the method:
    ```java
    try {
        divide(10, 0);
    } catch (ArithmeticException e) {
        System.out.println("Caught: " + e.getMessage());
    }
    ```
    Output:
    ```
    Caught: Division by zero
    ```

---

### **Custom Exceptions**
- Custom exceptions extend the `Exception` class.
- Example: `SoldeInsuffisantException` for insufficient balance.
  ```java
  class SoldeInsuffisantException extends Exception {
      SoldeInsuffisantException(String message) {
          super(message);
      }
  }

  void retirer(double montant) throws SoldeInsuffisantException {
      if (montant > solde) {
          throw new SoldeInsuffisantException("Solde insuffisant !");
      }
      solde -= montant;
  }
  ```
- Using the custom exception:
  ```java
  try {
      retirer(2000);
  } catch (SoldeInsuffisantException e) {
      System.out.println(e.getMessage());
  }
  ```
  Output:
  ```
  Solde insuffisant !
  ```

---

### **Key Takeaways**
- Exception handling ensures program robustness by managing errors gracefully.
- **Checked exceptions** are for predictable issues (e.g., file handling).
- **Unchecked exceptions** represent programming errors (e.g., null access).
- **Custom exceptions** are useful for domain-specific scenarios.

***

# Course 2 : Collections and Streams.
---
Here is a concise summary of the **Java Collections and Streams** document, with explanations and examples:

---

### **Collections in Java**
- **Definition:** Flexible and dynamic data structures to store and manipulate groups of objects.
- **Key Interfaces:**
  - `List`: Ordered collection. Example: `ArrayList`, `LinkedList`.
  - `Set`: Collection without duplicates. Example: `HashSet`.
  - `Map`: Key-value pairs. Example: `HashMap`.

#### **Examples:**
1. **ArrayList:**
   ```java
   List<String> list = new ArrayList<>();
   list.add("Ahmed");
   list.add("Said");
   System.out.println(list.get(0)); // Ahmed
   list.forEach(System.out::println); // Ahmed, Said
   list.clear(); // Clears the list
   ```

2. **Sorting with Collections:**
   ```java
   ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(4, 5, 2, 1));
   Collections.sort(numbers); // [1, 2, 4, 5]
   Collections.sort(numbers, Collections.reverseOrder()); // [5, 4, 2, 1]
   ```

3. **HashSet:**
   ```java
   HashSet<String> set = new HashSet<>();
   set.add("Java");
   set.add("Python");
   System.out.println(set.contains("Java")); // true
   set.remove("Python");
   ```

4. **HashMap:**
   ```java
   Map<String, Double> map = new HashMap<>();
   map.put("k1", 10.7);
   map.put("k2", 0.6);
   map.forEach((key, value) -> System.out.println(key + ": " + value)); // k1: 10.7, k2: 0.6
   map.remove("k1");
   ```

---

### **Streams in Java**
- **Definition:** A sequence of elements for processing data operations (introduced in Java 8).
- **Common Operations:**
  - `filter`: Selects elements matching a condition.
  - `map`: Transforms each element.
  - `flatMap`: Produces multiple elements per input.
  - `reduce`: Combines elements into a single result.
  - `count`: Counts elements in the stream.
  - `forEach`: Performs an action on each element.
  - `collect`: Converts stream elements into other forms like a `List`.

#### **Examples:**
1. **Filter:**
   ```java
   List<String> strings = Arrays.asList("Java", "Stream", "Example");
   List<String> filtered = strings.stream()
                                  .filter(s -> s.startsWith("S"))
                                  .collect(Collectors.toList());
   System.out.println(filtered); // [Stream]
   ```

2. **Map:**
   ```java
   List<String> upperCaseStrings = strings.stream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());
   System.out.println(upperCaseStrings); // [JAVA, STREAM, EXAMPLE]
   ```

3. **FlatMap:**
   ```java
   String concatenated = strings.stream()
                                .flatMap(s -> Arrays.stream(s.split("")))
                                .reduce("", String::concat);
   System.out.println(concatenated); // JavaStreamExample
   ```

4. **Reduce:**
   ```java
   String concatenatedString = strings.stream()
                                      .reduce("", String::concat);
   System.out.println(concatenatedString); // JavaStreamExample
   ```

5. **Count:**
   ```java
   long count = strings.stream().count();
   System.out.println(count); // 3
   ```

6. **ForEach:**
   ```java
   strings.stream().forEach(s -> System.out.println(s.toLowerCase()));
   // Output: java, stream, example
   ```

---

### **Key Takeaways**
- **Collections**: Provide flexibility in managing data.
- **Streams**: Enable efficient and declarative data processing with functional-style operations.
