package entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



@Entity
@Table(name="department")
public class Department {
	@TableGenerator(name = "department_gen",
			table = "id_gen1",
			pkColumnName = "gen_name",
			valueColumnName = "gen_val",
			allocationSize = 1,
			pkColumnValue = "department_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "department_gen")
	private int departmentID;
	@Column(name="name")
	private String name;
	@Column(name="manager of department")
	private String managerofdep;
	@Column(name="number of employees")
	private int numofemployees;
	@Column(name="idmanager")
	private int managerid;
	@Column(name="id of task")
	private int taskid;
	public Department() {}
	public Department(
			String name,
			String managerofdep,
			int numofemployees,
			int managerid,
			int taskid
			)
	{
		this.name = name;
		this.managerofdep=managerofdep;
		this.numofemployees=numofemployees;
		this.managerid=managerid;
		this.taskid=taskid;
	}
	public int GetDepartmentID() {return departmentID;}
	public String GetName() {return name;}
	public void SetName(String newname) {this.name = newname;}
	public String GetManagerofDep() {return managerofdep;}
	public void SetManagerofDep(String newmanager) {this.managerofdep = newmanager;}
	public int GetNumofEmployees() {return numofemployees;}
	public void SetNumofEmployees(int newnumofemployees) {this.numofemployees=newnumofemployees;}
	public int GetManagerId() {return managerid;}
	public void SetManagerId(int newmanagerid) {this.managerid = newmanagerid;}
	public int GetTaskID (){return taskid;}
	public void SetTaskID(int newtaskid){this.taskid=newtaskid;}
	
	public List<Task> getTasks(int taskid) {
	    List<Task> tasks = new ArrayList<>();
	    List<Task> alltasks = GetAllTasks();

	    for (Task task : alltasks) {
	        if (task.GetTaskId() == taskid) {
	            tasks.add(task);
	        }
	    }
	    return tasks;
	}
	private EntityManager em;

	private List<Task> GetAllTasks() {
		List<Task> alltasks = em.createQuery("SELECT t FROM task t", Task.class).getResultList();
		return alltasks;		
	}


}