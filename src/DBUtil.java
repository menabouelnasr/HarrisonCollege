import java.util.List;

import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class DBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HarrisonCollege");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static void insert(Object o) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(o);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Object o) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(o);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Object o) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(o));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}	
	
	public static java.util.List<Object> get(String qString) {
		return emf.createEntityManager().createQuery(qString, Object.class).getResultList();
	}
	
	public static java.util.List<Course> getCourse(String qString) {
		return emf.createEntityManager().createQuery(qString, Course.class).getResultList();
	}
	
	public static java.util.List<Department> getDepartment(String qString) {
		return emf.createEntityManager().createQuery(qString, Department.class).getResultList();
	}
	public static java.util.List<Instructor> getInstructor(String qString) {
		return emf.createEntityManager().createQuery(qString, Instructor.class).getResultList();
	}
	public static java.util.List<Time> getTime(String qString) {
		return emf.createEntityManager().createQuery(qString, Time.class).getResultList();
	}
	public static java.util.List<Enroll> getEnroll(String qString) {
		return emf.createEntityManager().createQuery(qString, Enroll.class).getResultList();
	}

	public static java.util.List<Student> getStudent(String qString) {
		return emf.createEntityManager().createQuery(qString, Student.class).getResultList();
	}
	
}