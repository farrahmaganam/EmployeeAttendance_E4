
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.EmployeeAlreadyExistsException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.InvalidAttendanceException;

/**
 * Manages employee registration and attendance records.
 */
public class AttendanceManager {
  private List<Employee> employees;
  private List<AttendanceRecord> attendanceRecords;
  
  public AttendanceManager() {
    this.employees = new ArrayList<>();
    this.attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers an employee unless an employee with the same ID already exists.
   *
   * @param employee the employee to register
   * @throws EmployeeAlreadyExistsException if the employee ID is already registered
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee existingEmployee : employees) {
      if (existingEmployee.getEmployeeId().equals(employee.getEmployeeId())) {
        throw new EmployeeAlreadyExistsException(
          "Employee with ID " + employee.getEmployeeId() + " already exists.");
      }
    }
    employees.add(employee);
  }

  /**
   * Finds an employee by their ID.
   *
   * @param employeeId the ID of the employee to find
   * @return the employee with the specified ID
   * @throws EmployeeNotFoundException if the employee is not found
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee employee : employees) {
      if (employee.getEmployeeId().equals(employeeId)) {
        return employee;
      }
    }
    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
  }


  /**
   * Records the time-in for an employee.
   *
   * @param employeeId the ID of the employee
   * @throws EmployeeNotFoundException if the employee is not found
   * @throws InvalidAttendanceException if the employee has already timed in
   */
  public void timeIn(String employeeId) 
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        throw new InvalidAttendanceException(
            "Employee " + employeeId + " is already timed in.");
      }
    }
      
    AttendanceRecord record = new AttendanceRecord(
        employeeId, 
        LocalDateTime.now(),
        null);
    attendanceRecords.add(record);
  }

  /**
   * Records the time-out for an employee.
   *
   * @param employeeId the ID of the employee
   * @throws EmployeeNotFoundException if the employee is not found
   * @throws InvalidAttendanceException if the employee has not timed in
   */
  public void timeOut(String employeeId)  
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    AttendanceRecord activeRecord = null;
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        activeRecord = record;
        break;
      }
    }

    if (activeRecord == null) {
      throw new InvalidAttendanceException(
        "No active time-in session found for employee " + employeeId + ".");
    }
    activeRecord.setTimeOut(LocalDateTime.now());
  }


  /**
   * Retrieves the completed attendance records for an employee.
   *
   * @param employeeId the ID of the employee
   * @return a list of completed attendance records
   * @throws EmployeeNotFoundException if the employee is not found
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId) 
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);
    return attendanceRecords.stream()
      .filter(record -> record.getEmployeeId().equals(employeeId) && record.getTimeOut() != null)
      .collect(Collectors.toList());
  } 

}
