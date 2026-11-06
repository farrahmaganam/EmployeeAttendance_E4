package Exceptions;

/**
 * Exception thrown when an employee is not found.
 * EmployeeNotFoundException
 */
public class EmployeeNotFoundException extends Exception {
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
