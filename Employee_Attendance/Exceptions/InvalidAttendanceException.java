package Exceptions;

/**
 * Exception thrown when an attendance record is invalid.
 * InvalidAttendanceException
 */
public class InvalidAttendanceException extends Exception {
  public InvalidAttendanceException(String message) {
    super(message);
  } 
}
