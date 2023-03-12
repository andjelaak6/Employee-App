package services.imp;
import java.time.*;
import services.*;
import entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Remote(EmployeeService.class)
@Stateless
public class EmployeeServiceImp implements EmployeeService {

	private EntityManager em;

	public EmployeeServiceImp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeApp");
		em = emf.createEntityManager();
	}
	
	public void AddEmployee(String fullname,String email,Date dateofbirth,int phonenumber,double salary) {
		em.getTransaction().begin();
		Employee e = new Employee(fullname, email,dateofbirth,
				phonenumber, salary);
		em.persist(e);
		em.getTransaction().commit();
		
	}

	public void RemoveEmployee(int id) {
		em.getTransaction().begin();
		Employee e = em.find(Employee.class, id);
		em.remove(e);
		em.getTransaction().commit();
	}

	public void UpdateEmployee(int id, String newfullname,String newemail,Date newdateofbirth,int newphonenumber,double newsalary) {
		Employee e = em.find(Employee.class, id);
		e.SetSalary(newsalary);
		e.SetDateofBirth(newdateofbirth);
		e.SetPhoneNumber(newphonenumber);
		e.SetEmail(newemail);
		e.SetName(newfullname);
	}

	public List<Employee> GetAllEmployees() {
		List<Employee> allemployees = em.createQuery("SELECT e FROM employee e", Employee.class).getResultList();
		return allemployees;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee e = em.find(Employee.class, id);
		return e;
	}
	
	public void GetEWithLargestNumofTasks(){
		 List<Employee> employees = GetAllEmployees();
		 LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);  
		 Map<Employee, Integer> tasksCompletedByEmployee = new HashMap<>();
	     for (Employee employee : employees) {
            int tasksCompleted = 0;
            for (Task task : employee.getCompletedTasks()) {
                if (task.GetDueDate().isAfter(oneMonthAgo)) {
                    tasksCompleted++;
                }
            }
            tasksCompletedByEmployee.put(employee, tasksCompleted);
        }
	    System.out.println("Top 5 employees who completed the largest number of tasks in the past month:");
        int count = 0;
        for (Employee employee : employees) {
            if (count == 5) {
                break;
            }
            System.out.println(employee.GetName() + " completed " + tasksCompletedByEmployee.get(employee) + " tasks");
            count++;
	     }


	
       }
	    public boolean IsManager(Employee employee) {
		Department department = new Department();
		if(employee.GetName()==department.GetName())
			return true;
		 else 
			return false;
	    }    
	
	   public List<Employee> getTopTwoManagers() {
	    List<Employee> managers = new ArrayList<>();
		List<Employee> employees = GetAllEmployees();
	    for (Employee employee : employees) {
	        if (IsManager(employee)) {
	            managers.add(employee);
	        }
	    }

	    Collections.sort(managers, new Comparator<Employee>() {
	        public int compare(Employee o1, Employee o2) {
	            return Double.compare(o2.GetSalary(), o1.GetSalary());
	        }
	    });

	    return managers.subList(0, Math.min(3, managers.size()));
	}
}