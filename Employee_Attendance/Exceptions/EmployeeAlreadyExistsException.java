package Exceptions;

/**
 * Exception thrown when attempting to register an employee that already exists.
 * EmployeeAlreadyExistsException
 */
public class EmployeeAlreadyExistsException extends Exception {
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }
}
