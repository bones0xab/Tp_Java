# Course 1 : OOP
---
# Exception Managements in JAVA.
--- 

Here is a summary of the document about **Exception Handling in Java**, presented in bullet points:

### General Concepts of Exceptions
- An **exception** is an unexpected event during program execution that disrupts the normal flow of instructions.
- **Difference between an error and an exception:**
  - Exceptions can be caught and handled by the programmer.
  - Errors are severe issues related to the JVM and cannot be handled programmatically (e.g., OutOfMemoryError).

### Exception Class Hierarchy
- Root class: `Throwable` (a subclass of `Object`).
  - **Error**: Represents critical issues within the JVM (not recoverable).
  - **Exception**: Represents less severe, recoverable issues.
    - **RuntimeException**: A subclass of `Exception` for unchecked exceptions.

### Types of Exceptions
1. **Checked (Surveillées):**
   - Verified at compile-time.
   - Must be handled using `try-catch` blocks or declared with `throws`.
   - Examples: `IOException`, `SQLException`.
2. **Unchecked (Non-surveillées):**
   - Not verified at compile-time.
   - Occur at runtime (e.g., `ArithmeticException` for division by zero).
   - Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`.

### Handling Exceptions
- **Using `try-catch` blocks:**
  - `try`: Contains code that might throw an exception.
  - `catch`: Handles the exception.
  - `finally` (optional): Executes code regardless of whether an exception occurs.
- **Multiple catch blocks:** Handle different exception types in sequence.
- **Example:** Catching and handling `ArithmeticException` and continuing program execution.

### Propagating Exceptions
- **Using `throw`:** Explicitly throws an exception in a method.
- **Using `throws`:** Declares potential exceptions that a method might throw.
- Exception not caught in a method propagates to the calling method or JVM.

### Custom Exceptions
- Developers can define custom exceptions by extending the `Exception` class.
- Naming convention: Include "Exception" in the class name.
- Example: `SoldeInsuffisantException` for insufficient account balance.

### Key Takeaways
- Exception handling ensures program robustness by gracefully managing errors.
- Use **checked exceptions** for predictable and recoverable errors.
- Reserve **custom exceptions** for domain-specific error scenarios.
