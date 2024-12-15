<h1 align="center">CheatSheet</h1>

 ***

 [Course 1 : Exceptions handling](#course1)
 
 [Course 2 : Collections and Streams](#course2)

 [Course 3 : javaFX](#course3)

 [Course 4 : JDBC](#course4)

 [Course 5 : Generitic](#course5)

 [Course 6 : Files IO](#course6)

  


# <a name="course1"></a>Course 1 : Exceptions handling. 
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

# <a name="course2"></a> Course 2 : Collections and Streams.
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


***

# <a id="course3"></a> Course 3 : JavaFX
---
Here is a summarized breakdown of the **JavaFX** document with examples for each explanation:

---

### **What is JavaFX?**
- **Definition:** JavaFX is a library for building modern graphical user interfaces (GUIs) for desktop, mobile, and web applications.
- **Key Points:**
  - Official GUI library for Java since Java 8 (March 2014), replacing Swing.
  - Allows for high-quality, dynamic, and interactive UI designs.

---

### **Structure of a JavaFX Application**
- **Main Components:**
  - **Stage:** Represents the main application window.
  - **Scene:** Holds all visual elements of the application.
  - **Graphic Nodes:** Include:
    - **User controls:** `Label`, `TextField`, `Button`, etc.
    - **Shapes:** `Circle`, `Rectangle`, `Line`.
    - **Media:** `ImageView`, `MediaView`.
    - **Layouts:** Used for organizing elements (`BorderPane`, `VBox`, etc.).

#### **Example: Basic Application**
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click Me");
        StackPane layout = new StackPane(button);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

### **Layouts in JavaFX**
- **Purpose:** Manage and arrange UI components in an application window.
- **Key Layout Types:**
  1. **BorderPane:** Divides the window into five areas (Top, Bottom, Left, Right, Center).
     ```java
     BorderPane layout = new BorderPane();
     layout.setTop(new Button("Top"));
     layout.setBottom(new Button("Bottom"));
     ```
  2. **HBox:** Arranges elements horizontally.
     ```java
     HBox layout = new HBox(10, new Button("Button1"), new Button("Button2"));
     ```
  3. **VBox:** Arranges elements vertically.
     ```java
     VBox layout = new VBox(10, new Button("Button1"), new Button("Button2"));
     ```
  4. **GridPane:** Creates a grid layout.
     ```java
     GridPane grid = new GridPane();
     grid.add(new Button("Button1"), 0, 0);
     grid.add(new Button("Button2"), 1, 0);
     ```
  5. **AnchorPane:** Fixes elements to the edges of the window.
     ```java
     AnchorPane layout = new AnchorPane();
     AnchorPane.setTopAnchor(button, 10.0);
     ```

---

### **FXML-Based Method**
- **Definition:** JavaFX allows UI structure to be defined using an XML-based format (FXML).
- **Advantages:**
  - Separates UI design (FXML) from application logic (Controller).
  - Simplifies large UI designs.
- **Process:**
  1. Define UI in an FXML file.
  2. Create a controller class to handle events and logic.
  3. Load the FXML file in the Java application.

#### **Example: FXML File**
```xml
<BorderPane xmlns:fx="http://javafx.com/fxml" fx:controller="myapp.Controller">
    <top>
        <HBox spacing="10">
            <Label text="Name: "/>
            <TextField fx:id="nameField"/>
            <Button text="Submit" onAction="#handleSubmit"/>
        </HBox>
    </top>
</BorderPane>
```

#### **Example: Controller**
```java
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;

    public void handleSubmit() {
        System.out.println("Name: " + nameField.getText());
    }
}
```

#### **Example: Main Application**
```java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
        Scene scene = new Scene(loader.load(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

### **Key Takeaways**
- **JavaFX Features:**
  - Rich and modern GUI development.
  - Supports both programmatic and declarative (FXML) approaches.
- **Layouts:** Offer flexibility in arranging UI elements.
- **FXML:** Simplifies the development and separation of UI and logic.



***

#<a id="course4"></a> Course 4 : JDBC 
---
# JDBC and JavaFX Database Integration Guide

## 1. JDBC Fundamentals

JDBC (Java Database Connectivity) serves as a bridge between Java applications and databases:

* Acts as a Java library enabling database communication over TCP/IP
* Each DBMS requires specific JDBC drivers
* Special JdbcOdbcDriver allows universal data source access via ODBC
* Functions as an SQL query execution interface

**Basic Driver Loading:**
```java
// Loading MySQL JDBC driver
Class.forName("com.mysql.jdbc.Driver");
```

## 2. JDBC Application Development Process

### Essential Steps:
1. Driver Loading
2. Data Source Identification
3. Connection Establishment
4. Statement Creation
5. Query Execution
6. Result Processing
7. Resource Cleanup

**Connection Example:**
```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/DB",
    "user", 
    "password"
);
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM table");
```

## 3. Statement Types and Usage

### Standard Statement
```java
Statement stmt = connection.createStatement();
stmt.executeQuery("SELECT * FROM products");
stmt.executeUpdate("INSERT INTO products VALUES(1, 'Product')");
```

### PreparedStatement
```java
PreparedStatement pstmt = connection.prepareStatement(
    "INSERT INTO products VALUES(?, ?)"
);
pstmt.setInt(1, 100);
pstmt.setString(2, "Product Name");
pstmt.executeUpdate();
```

## 4. Three-Tier Architecture

### Layer Structure:
* **DAO Layer**: Database operations
* **Business Layer**: Processing logic
* **Presentation Layer**: MVC implementation

**Singleton Connection Pattern:**
```java
public class SingletonConnectionDB {
    private static Connection connection;
    
    private SingletonConnectionDB() {}
    
    public static Connection getConnection() {
        if(connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
```

## 5. Object-Relational Mapping

### Key Concepts:
* Database records map to Java objects
* Persistent classes represent database tables
* Bridges object-oriented and relational models

**Persistent Class Example:**
```java
public class Product {
    private int id;
    private String name;
    private double price;
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    // Additional getters/setters
}
```

## 6. JavaFX Integration

### Implementation Features:
* FXML-based UI design
* Controller-managed business logic
* MVC pattern implementation

**Controller Implementation:**
```java
public class ProduitController implements Initializable {
    @FXML
    private TableView<Product> tableView;
    private IMetier metier;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        metier = new MetierImpl();
        loadData();
    }
    
    private void loadData() {
        tableView.getItems().setAll(metier.getAllProducts());
    }
}
```

## 7. Best Practices

### Application Design Principles:
* Maintain separation of concerns
* Implement proper error handling
* Follow secure coding practices
* Use prepared statements for queries
* Close resources properly

**Resource Management Example:**
```java
try (Connection conn = getConnection();
     PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)) {
    // Execute operations
    pstmt.executeUpdate();
} catch (SQLException e) {
    // Handle exceptions
} // Resources automatically closed
```

## 8. Benefits of This Architecture

1. Enhanced maintainability
2. Improved scalability
3. Better security
4. Clean code organization
5. Efficient resource management
6. Clear separation of responsibilities

This structure provides a robust foundation for building enterprise-level Java applications with database integration, ensuring both functionality and maintainability.


***

#<a id="course5"></a> Course 5 : Generitic.
---

Let me provide a clearer, more conceptual summary of Java Generics with simpler explanations followed by examples.

# Understanding Java Generics

## 1. What are Generics?
Generics are like templates in Java that let you write code that can work with different types of data. Think of it as creating a box that can hold any type of item you specify when you create it.

For example:
```java
// A box that can hold any type
Box<String> stringBox = new Box<>("Hello");  // A box for strings
Box<Integer> numberBox = new Box<>(123);     // A box for numbers
```

## 2. Generic Classes
Think of a generic class as a blueprint for creating objects that can work with any data type you choose.

**Real-world analogy**: It's like having a container factory that can make containers for any specific type of item:
```java
// Container that can hold anything
public class Container<T> {
    private T item;
    
    public void store(T item) { this.item = item; }
    public T retrieve() { return item; }
}

// Usage
Container<Coffee> coffeeContainer = new Container<>();
Container<Sugar> sugarContainer = new Container<>();
```

## 3. Generic Methods
These are methods that can work with different types in a single implementation.

**Real-world analogy**: Like a washing machine that can wash any type of clothing:
```java
public <T> void printItems(T[] items) {
    for(T item : items) {
        System.out.println(item);
    }
}

// Use it with any type
printItems(new String[]{"apple", "banana"});
printItems(new Integer[]{1, 2, 3});
```

## 4. Type Constraints
You can limit what types can be used with your generic code.

**Real-world analogy**: Like a vending machine that only accepts certain types of coins:
```java
// Only accepts Numbers (Integer, Double, etc.)
public class NumberBox<T extends Number> {
    private T number;
}
```

## 5. Generic Interfaces
Define contracts that can work with any type.

**Real-world analogy**: Like a recipe that can be used with different ingredients:
```java
public interface Storage<T> {
    void add(T item);
    T get();
}

// Implementation for strings
class StringStorage implements Storage<String> {
    // Implementation details
}
```

## 6. Why Use Generics?

1. **Type Safety**: Catches errors at compile time
   ```java
   List<String> names = new ArrayList<>();
   names.add("John");     // OK
   names.add(123);        // Won't compile - caught early!
   ```

2. **No Casting Needed**: The compiler knows the type
   ```java
   // Without generics
   List oldList = new ArrayList();
   String name = (String) oldList.get(0);  // Need casting

   // With generics
   List<String> newList = new ArrayList<>();
   String name = newList.get(0);           // No casting needed
   ```

3. **Code Reusability**: Write once, use with many types
   ```java
   public class Pair<T> {
       private T first, second;
       // Can be used with any type!
   }
   ```

## 7. Common Uses
- Collections (List<String>, Map<String, Integer>)
- Custom data structures (Tree<E>, Stack<T>)
- Service layers (Service<EntityType>)
- Utility methods that work with multiple types

This approach gives you:
- Type safety at compile time
- Cleaner code without type casting
- More reusable components
- Better design patterns implementation

Remember: Generics only exist at compile time - they're erased at runtime (type erasure), but they help catch errors early and make your code more robust.


***

#<a id="course6"></a> Course 6 : Files Manipulations
---

# Java Input/Output (I/O) for Files - A Comprehensive Guide

## 1. Introduction to File I/O in Java

File I/O in Java allows you to read from and write to files. Think of it as having conversations with files:
- Reading is like listening to what the file says
- Writing is like speaking to the file

## 2. File Streams

### Basic Concept
Think of streams as pipes that connect your program to files:
- Input streams bring data in (reading)
- Output streams send data out (writing)

```java
// Reading from a file (Input)
FileInputStream fis = new FileInputStream("input.txt");

// Writing to a file (Output)
FileOutputStream fos = new FileOutputStream("output.txt");
```

## 3. Text File Handling

### BufferedReader (Reading text files)
Like reading a book line by line:
```java
try (BufferedReader reader = new BufferedReader(new FileReader("story.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

### BufferedWriter (Writing text files)
Like writing in a notebook:
```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"))) {
    writer.write("Hello World!");
    writer.newLine();  // Add a new line
    writer.write("This is a new line");
}
```

## 4. Binary File Handling

### For raw data (like images, videos)
```java
// Reading binary file
try (FileInputStream fis = new FileInputStream("image.jpg")) {
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = fis.read(buffer)) != -1) {
        // Process bytes
    }
}

// Writing binary file
try (FileOutputStream fos = new FileOutputStream("copy.jpg")) {
    byte[] data = // your binary data
    fos.write(data);
}
```

## 5. File Class Operations

### Basic File Operations
```java
File file = new File("example.txt");

// Check if file exists
boolean exists = file.exists();

// Create new file
boolean created = file.createNewFile();

// Delete file
boolean deleted = file.delete();

// Get file information
long size = file.length();
boolean isDirectory = file.isDirectory();
```

## 6. Scanner Class
Perfect for reading formatted input:
```java
try (Scanner scanner = new Scanner(new File("data.txt"))) {
    // Read different data types
    String name = scanner.nextLine();
    int age = scanner.nextInt();
    double salary = scanner.nextDouble();
}
```

## 7. PrintWriter
Great for formatted output:
```java
try (PrintWriter writer = new PrintWriter("output.txt")) {
    writer.println("Name: John");
    writer.printf("Age: %d", 25);
    writer.print("This is without newline");
}
```

## 8. Best Practices

### 1. Always Use Try-with-Resources
```java
try (FileReader reader = new FileReader("file.txt")) {
    // Code here
} catch (IOException e) {
    // Handle exception
}
```

### 2. Buffer Usage for Better Performance
```java
// Instead of
FileReader reader = new FileReader("file.txt");

// Use
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
```

### 3. Error Handling
```java
try {
    // File operations
} catch (FileNotFoundException e) {
    // Handle missing file
} catch (IOException e) {
    // Handle other I/O problems
}
```

## 9. Common Use Cases

### 1. Reading Configuration Files
```java
Properties props = new Properties();
try (FileInputStream fis = new FileInputStream("config.properties")) {
    props.load(fis);
    String value = props.getProperty("key");
}
```

### 2. Logging
```java
try (FileWriter fw = new FileWriter("log.txt", true)) { // true for append mode
    fw.write(new Date() + ": Application started\n");
}
```

## 10. Key Points to Remember

1. **Always Close Resources**
   - Use try-with-resources when possible
   - Prevents memory leaks and file system issues

2. **Choose the Right Tool**
   - Text files: BufferedReader/BufferedWriter
   - Binary files: FileInputStream/FileOutputStream
   - Formatted text: Scanner/PrintWriter

3. **Error Handling is Critical**
   - Files might not exist
   - Permissions might be inadequate
   - Disk might be full

4. **Performance Considerations**
   - Use buffered streams for better performance
   - Consider buffer sizes for large files
   - Be mindful of memory usage with large files

This overview gives you the foundation to handle most file operations in Java. Start with simple text file operations and gradually move to more complex scenarios as needed.

***
