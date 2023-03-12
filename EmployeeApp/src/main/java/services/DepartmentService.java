package services;

import java.util.List;

import entities.Department;


public interface DepartmentService {
	public void AddDepartment(String name,String managerofdep,int numofemployees,int managerid,int taskid);
	public void RemoveDepartment(int id);
	public void UpdateDepartment(int id, String newname,String newmanagerofdep,int newnumofemployees,int newmanagerid,int newtaskid);
	public List<Department> GetAllDepartments();
	public Department getDepartment(int id);

}