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


***

# Course 3 : JavaFX
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

# Course 4 : JDBC 
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

# Course 5 : Generitic.
---

# Java Generics Guide

## 1. Introduction to Generics

Generics in Java allows creating classes, methods, and interfaces that can work with different data types.

Key Points:
* Introduced in Java 1.5
* Enables type-independent code structure
* Promotes code reuse
* Provides type safety at compile time

## 2. Generic Classes

### Basic Generic Class Definition
```java
public class Container<T> {
    private T value;
    
    public Container(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}
```

### Usage Example
```java
// Integer container
Container<Integer> intContainer = new Container<>(42);
// String container
Container<String> stringContainer = new Container<>("Hello");
```

## 3. Extending Generic Classes

### Generic Subclass
```java
// Parent class
public class Pair<T> {
    private T first;
    private T second;
    // constructors and methods
}

// Child class maintaining generic type
public class Triple<T> extends Pair<T> {
    private T third;
    // constructors and methods
}
```

### Non-Generic Subclass
```java
// Specific type implementation
public class StringPair extends Pair<String> {
    // Implements Pair specifically for Strings
    public StringPair(String first, String second) {
        super(first, second);
    }
}
```

## 4. Generic Type Constraints

You can restrict generic types using extends keyword:

```java
// T must be a subtype of Employee and implement both interfaces
public class EmployeeContainer<T extends Employee & Serializable & Cloneable> {
    private T employee;
    
    public void setEmployee(T employee) {
        this.employee = employee;
    }
}
```

## 5. Generic Methods

Methods can be generic regardless of whether they're in a generic class:

```java
public class Utilities {
    // Generic method
    public <T> void printArray(T[] array) {
        for(T element : array) {
            System.out.println(element);
        }
    }
}

// Usage
Utilities utils = new Utilities();
String[] strings = {"Hello", "World"};
utils.printArray(strings);
```

## 6. Generic Interfaces

### Interface Definition
```java
public interface Repository<T> {
    T findById(long id);
    void save(T item);
    void delete(T item);
    List<T> findAll();
}
```

### Implementation Example
```java
public class UserRepository implements Repository<User> {
    @Override
    public User findById(long id) {
        // Implementation
        return null;
    }
    
    @Override
    public void save(User item) {
        // Implementation
    }
    
    @Override
    public void delete(User item) {
        // Implementation
    }
    
    @Override
    public List<User> findAll() {
        // Implementation
        return new ArrayList<>();
    }
}
```

## 7. Best Practices

1. Type Naming Conventions:
   * T for general type
   * E for element
   * K for key
   * V for value
   * N for number

2. Use wildcards appropriately:
```java
// Accept any list of numbers
public void processNumbers(List<? extends Number> numbers) {
    // Process numbers
}
```

3. Provide type bounds when necessary:
```java
public <T extends Comparable<T>> T findMax(List<T> list) {
    // Find maximum value
    return null;
}
```

## 8. Benefits of Generics

1. Type Safety
   * Catches errors at compile time rather than runtime

2. Code Reusability
   * Write once, use with many types

3. No Type Casting
   * Eliminates explicit casting in code

4. Generic Algorithms
   * Implement algorithms independent of type

This structured approach to generics provides a robust foundation for creating flexible and type-safe Java applications.
