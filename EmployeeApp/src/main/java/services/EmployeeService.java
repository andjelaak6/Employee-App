package services;


import java.util.Date;
import java.util.List;


import entities.Employee;

public interface EmployeeService {
	public void AddEmployee(String fullname,String email,Date dateofbirth,int phonenumber,double salary);
	public void RemoveEmployee(int id);
	public void UpdateEmployee(int id, String newfullname,String newemail,Date newdateofbirth,int newphonenumber,double newsalary);
	public List<Employee> GetAllEmployees();
	public void GetEWithLargestNumofTasks();
	public Employee getEmployee(int id);
	public boolean IsManager(Employee employee);
	public List<Employee> getTopTwoManagers();
}
