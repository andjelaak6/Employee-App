package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import entities.Task;


@Entity
@Table(name="employee")
public class Employee {
	@TableGenerator(name = "employee_gen",
			table = "id_gen1",
			pkColumnName = "gen_name",
			valueColumnName = "gen_val",
			allocationSize = 1,
			pkColumnValue = "employee_gen")

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")
	private int employeeID;
	@Column(name="full name")
	private String fullname;
	@Column(name="email")
	private String email;
	@Column(name="date of birth")
	private Date dateofbirth;
	@Column(name="phone number")
	private int phonenumber;
	@Column(name="monthly salary")
	private double salary;
	public Employee() {}
	public Employee(String fullname,String email,Date dateofbirth,int phonenumber,double salary)
	{
		this.fullname = fullname;
		this.email=email;
		this.dateofbirth=dateofbirth;
		this.phonenumber=phonenumber;
		this.salary=salary;
	}
	public int GetEmployeeID() {return employeeID;}
	public String GetName() {return fullname;}
	public void SetName(String newfullname) {this.fullname = newfullname;}
	public String GetEmail() {return email;}
	public void SetEmail(String newemail) {this.email = newemail;}
	public Date GetDateofBirth() {return dateofbirth;}
	public void SetDateofBirth(Date newdateofbirth) {this.dateofbirth = newdateofbirth;}
	public int GetPhoneNumber() {return phonenumber;}
	public void SetPhoneNumber(int newphonenumber) {this.phonenumber=newphonenumber;}
	public double GetSalary() {return salary;}
	public void SetSalary(double newsalary) {this.salary = newsalary;}
	private EntityManager em;
	public List<Task> getCompletedTasks() {
		List<Task> alltasks = em.createQuery("SELECT t FROM task t", Task.class).getResultList();
		List<Task> completedtasks = new ArrayList<>();
		LocalDate today = LocalDate.now();
	    
	    for (Task task : alltasks) {
	        if (task.isCompleted() && task.GetDueDate().isBefore(today)) {
	            completedtasks.add(task);
	        }
	    }
	    
	    return completedtasks;
	    }
	

}