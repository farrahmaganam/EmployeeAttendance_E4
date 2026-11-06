import java.time.LocalDateTime;

/**
 * Stores an employee's attendance times for a work session.
 */
public class AttendanceRecord {
  private String employeeId;
  private LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Creates a new AttendanceRecord with the specified employee ID, time-in, and time-out.
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn, LocalDateTime timeOut) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }
}
