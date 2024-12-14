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
