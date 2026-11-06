import Exceptions.EmployeeAlreadyExistsException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.InvalidAttendanceException;
import java.time.format.DateTimeFormatter;

/**
 * Main class for the Employee Attendance Management System.
 */
public class Main {
  /** The date and time format for displaying attendance records. */
  private static final DateTimeFormatter DATE_TIME_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  /**
   * Runs the employee attendance management demo.
   */
  public static void main(String[] args) {
    AttendanceManager attendanceManager = new AttendanceManager();

    System.out.println("Register employees:");
    registerEmployee(attendanceManager, new Employee("E001", "Riri Williams", "IT"));
    registerEmployee(attendanceManager, new Employee("E002", "Nick Fury", "HR"));

    System.out.println("\nRegister another employee:");
    registerEmployee(attendanceManager, new Employee("E001", "Thor Son of Odin", "Electrical"));

    System.out.println("\nSearching for employee E001:");
    try {
      displayEmployee(attendanceManager.findEmployeeById("E001"));
    } catch (EmployeeNotFoundException exception) {
      System.out.println(exception.getMessage());
    }

    System.out.println("\nSearching for employee E999:");
    try {
      displayEmployee(attendanceManager.findEmployeeById("E999"));
    } catch (EmployeeNotFoundException exception) {
      System.out.println(exception.getMessage());
    }

    System.out.println("\nRecording time-in for E001:");
    recordTimeIn(attendanceManager, "E001");

    System.out.println("\nAttempting a second time-in for E001:");
    recordTimeIn(attendanceManager, "E001");

    System.out.println("\nAttempting time-out for E002 without an active time-in:");
    recordTimeOut(attendanceManager, "E002");

    System.out.println("\nRecording time-out for E001:");
    recordTimeOut(attendanceManager, "E001");

    System.out.println("\nRecording another time-in for E001:");
    recordTimeIn(attendanceManager, "E001");

    System.out.println("\nCompleted attendance records for E001:");
    try {
      attendanceManager.getCompletedAttendanceRecords("E001").forEach(record ->
                System.out.printf("Time-in: %s, Time-out: %s%n",
                        record.getTimeIn().format(DATE_TIME_FORMAT),
                        record.getTimeOut().format(DATE_TIME_FORMAT)));
    } catch (EmployeeNotFoundException exception) {
      System.out.println(exception.getMessage());
    }
  }

  private static void registerEmployee(AttendanceManager attendanceManager, Employee employee) {
    try {
      attendanceManager.registerEmployee(employee);
      System.out.printf("Registered: %s - %s (%s)%n",
          employee.getEmployeeId(), employee.getName(), employee.getDepartment());
    } catch (EmployeeAlreadyExistsException exception) {
      System.out.println(exception.getMessage());
    }
  }

  private static void displayEmployee(Employee employee) {
    System.out.printf("ID: %s, Name: %s, Department: %s%n",
        employee.getEmployeeId(), employee.getName(), employee.getDepartment());
  }

  private static void recordTimeIn(AttendanceManager attendanceManager, String employeeId) {
    try {
      attendanceManager.timeIn(employeeId);
      System.out.println("Time-in recorded for employee " + employeeId + ".");
    } catch (EmployeeNotFoundException | InvalidAttendanceException exception) {
      System.out.println(exception.getMessage());
    }
  }

  private static void recordTimeOut(AttendanceManager attendanceManager, String employeeId) {
    try {
      attendanceManager.timeOut(employeeId);
      System.out.println("Time-out recorded for employee " + employeeId + ".");
    } catch (EmployeeNotFoundException | InvalidAttendanceException exception) {
      System.out.println(exception.getMessage());
    }
  }
}