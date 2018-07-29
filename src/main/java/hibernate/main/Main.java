package hibernate.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.model.Employee;
import hibernate.model.Project;
import hibernate.util.HibernateUtil;

public class Main {

	
	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
			// start transaction
			tx = session.beginTransaction();
			
			Employee emp=new Employee();
			emp.setName("namdev");

			Set<Project> projectsList=new HashSet<>();
			
			Project p1=new Project();
			p1.setName("Triton");
			
			Project p2=new Project();
			p2.setName("Godrej");
			
			Project p3=new Project();
			p3.setName("Kitaboo");

			session.save(p1);
			
			projectsList.add(p1);
			projectsList.add(p2);
			projectsList.add(p3);
			
			emp.setProjects(projectsList);
			
			session.save(emp);
			tx.commit();

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

}
