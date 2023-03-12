package services.imp;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Department;
import services.DepartmentService;

@Remote(DepartmentService.class)
@Stateless
public class DepartmentServiceImp implements DepartmentService {

	private EntityManager em;

	public DepartmentServiceImp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeApp");
		em = emf.createEntityManager();
	}
	
	public void AddDepartment(String name,String managerofdep,int numofemployees,int managerid,int taskid) {
		em.getTransaction().begin();
		Department d = new Department(name, managerofdep,numofemployees,managerid,taskid);
		em.persist(d);
		em.getTransaction().commit();
		
	}

	public void RemoveDepartment(int id) {
		em.getTransaction().begin();
		Department e = em.find(Department.class, id);
		em.remove(e);
		em.getTransaction().commit();
	}

	public void UpdateDepartment(int id, String newname,String newmanagerofdep,int newnumofemployees,int newmanagerid,int newtaskid) {
		Department d = em.find(Department.class, id);
		d.SetName(newname);
		d.SetManagerId(newmanagerid);
		d.SetManagerofDep(newmanagerofdep);
		d.SetNumofEmployees(newnumofemployees);
		d.SetTaskID(newtaskid);
	}

	public List<Department> GetAllDepartments() {
		List<Department> allDepartments = em.createQuery("SELECT d FROM department d", Department.class).getResultList();
		return allDepartments;
	}

	@Override
	public Department getDepartment(int id) {
		Department d = em.find(Department.class, id);
		return d;
	}
    
	
}
