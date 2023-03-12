package controllers;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import services.DepartmentService;
import entities.Department;
@ManagedBean
public class DepartmentController {
	
	private int id=0;
	private Department Department = new Department();
	
	
	@EJB
	private DepartmentService service;
	public Department getDepartment() {
		return Department;
	}
	public void setDepartment(Department Department) {
		this.Department = Department;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void CreateDepartment(String name,String managerofdep,int numofemployees,int managerid,int taskid) {
		service.AddDepartment(name,managerofdep,numofemployees,managerid,taskid);
	}
	
	public void DeleteDepartment(int id) {
		service.RemoveDepartment(id);
	}
	
	public void UpdateDepartment(int id, String newname,String newmanagerofdep,int newnumofemployees,int newmanagerid,int newtaskid) {
		service.UpdateDepartment(id, newname,newmanagerofdep,newnumofemployees,newmanagerid,newtaskid);
	}
	
	public List<Department> listDepartments(){
   		List<Department> allDepartments=service.GetAllDepartments();
   		return allDepartments;
   	}
	
	

	
}