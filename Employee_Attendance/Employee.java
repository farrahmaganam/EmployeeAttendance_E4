/**
 * Employee class represents an employee with their ID, name, and department.
 */
public class Employee {
  private String employeeId;
  private String name;
  private String department;

  /**
  * Creates a new Employee with the specified employee ID, name, and department.
  */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public String getDepartment() {
    return department;
  }
}
