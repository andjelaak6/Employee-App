package controllers;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import services.TaskService;
import entities.Task;
@ManagedBean
public class TaskController {
	
	private int id=0;
	private Task Task = new Task();
	
	
	@EJB
	private TaskService service;
	public Task getTask() {
		return Task;
	}
	public void setTask(Task Task) {
		this.Task = Task;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void CreateTask(String title,String description,String assignee,LocalDate duedate) {
		service.AddTask(title,description,assignee,duedate);
	}
	
	public void DeleteTask(int id) {
		service.RemoveTask(id);
	}
	
	public void UpdateTask(int id, String newtitle, String newdescription, String newassignee,LocalDate newduedate ) {
		service.UpdateTask(id, newtitle,newdescription,newassignee,newduedate);
	}
	
	public List<Task> listTasks(){
   		List<Task> allTasks=service.GetAllTasks();
   		return allTasks;
   	}
	public String BestManager() {
		String bm = service.BestManager();
		return bm;
	}

	
}