package services.imp;
import entities.Task;
import entities.Department;
import services.TaskService;
//import services.EmployeeService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Remote(TaskService.class)
@Stateless
public class TaskServiceImp implements TaskService {

	private EntityManager em;

	public TaskServiceImp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeApp");
		em = emf.createEntityManager();
	}
	
	public void AddTask(String title,String description,String assignee,LocalDate duedate) {
		em.getTransaction().begin();
		Task t = new Task(title,description,assignee,duedate);
		em.persist(t);
		em.getTransaction().commit();
		
	}

	public void RemoveTask(int id) {
		em.getTransaction().begin();
		Task t = em.find(Task.class, id);
		em.remove(t);
		em.getTransaction().commit();
	}

	public void UpdateTask(int id, String newtitle, String newdescription, String newassignee,LocalDate newduedate) {
		Task t = em.find(Task.class, id);
		t.SetTitle(newtitle);
		t.SetDescription(newdescription);
		t.SetDueDate(newduedate);
		t.SetAssignee(newassignee);
	}

	public List<Task> GetAllTasks() {
		List<Task> alltasks = em.createQuery("SELECT t FROM task t", Task.class).getResultList();
		return alltasks;
	}

	@Override
	public Task getTask(int id) {
		Task e = em.find(Task.class, id);
		return e;
	}
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
	
	public String BestManager() {
		Department department = new Department();
    	HashMap<String, Integer> taskCounts = new HashMap<>();
		 for (Task task : department.getTasks(department.GetTaskID()))
		 { 
			 
    	    String managerName = department.GetManagerofDep();
    	    
    	    if (!taskCounts.containsKey(managerName)) {
    	        taskCounts.put(managerName, 1);
    	    } 
    	    else {
    	        taskCounts.put(managerName, taskCounts.get(managerName) + 1);
    	    }
    	 }
    	String mostTasksManager = "";
    	int mostTasksCount = 0;
    	for (String managerName : taskCounts.keySet()) {
    	    int tasksCompleted = taskCounts.get(managerName);
    	    if (tasksCompleted > mostTasksCount) {
    	        mostTasksCount = tasksCompleted;
    	        mostTasksManager = managerName;
    	    }
    	}
       

    
		 return mostTasksManager;


	
	

	
   }
}





