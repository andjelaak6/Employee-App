package controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import services.EmployeeService;
import entities.Employee;
@ManagedBean
public class EmployeeController {
	
	private int id=0;
	private Employee employee = new Employee();
	
	
	
	@EJB
	private EmployeeService service;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void CreateEmployee(String fullname,String email,Date dateofbirth,int phonenumber,double salary) {
		service.AddEmployee(fullname,email,dateofbirth,phonenumber,salary);
	}
	
	public void DeleteEmployee(int id) {
		service.RemoveEmployee(id);
	}
	
	public void UpdateEmployee(int id,String newfullname,String newemail,Date newdateofbirth,int newphonenumber,double newsalary) {
		service.UpdateEmployee(id,newfullname,newemail,newdateofbirth,newphonenumber,newsalary);
	}
	
	public List<Employee> listEmployees(){
   		List<Employee> allEmployees=service.GetAllEmployees();
   		return allEmployees;
   	}
	public void GetEWithLargestNumofTasks() {
		service.GetEWithLargestNumofTasks();
	}
	public List<Employee> getTopTwoManagers() {
	    List<Employee> toptwom = new ArrayList<>();
	    toptwom = service.getTopTwoManagers();
   		return toptwom;
	}
	

	
}