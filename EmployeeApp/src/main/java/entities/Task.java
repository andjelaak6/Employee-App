package entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
//import java.util.Date;


@Entity
@Table(name="task")
public class Task {
	@TableGenerator(name = "task_gen",
			table = "id_gen2",
			pkColumnName = "gen_name",
			valueColumnName = "gen_val",
			allocationSize = 1,
			pkColumnValue = "task_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "task_gen")
	private int idtask;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="asignee")
	private String assignee;
	@Column(name="due date")
	private LocalDate duedate;
    private boolean completed;
    public void markCompleted() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(duedate);
    }
	public Task() {}
	public Task(
			String title,
			String description,
			String assignee,
			LocalDate duedate
			)
	{
		this.title = title;
		this.description = description;
		this.duedate = duedate ;
		this.assignee=assignee;
	}
	public int GetTaskId() {return idtask;}
	public String GetTitle() {return title;}
	public void SetTitle(String newtitle) {this.title = newtitle;}
	public String GetDescription() {return description;}
	public void SetDescription(String newdescription) {this.description= newdescription;}
	public LocalDate GetDueDate() {return duedate;}
	public void SetDueDate(LocalDate newdate) {this.duedate = newdate;}
	public String GetAssignee() {return assignee;}
	public void SetAssignee(String newassignee) {this.assignee = newassignee;}

}
