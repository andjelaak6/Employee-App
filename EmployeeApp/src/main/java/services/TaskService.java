package services;
import java.time.LocalDate;

import java.util.List;
import entities.Task;
//import entities.Employee;


public interface TaskService {
	public void AddTask(String title,String description,String assignee,LocalDate duedate);
	public void RemoveTask(int id);
	public Task getTask(int id);
	public List<Task> GetAllTasks();
	public void UpdateTask(int id, String newtitle, String newdescription, String newassignee,LocalDate newduedate );
    public String BestManager();
}
