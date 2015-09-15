package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HarrisonCollege");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	public static java.util.List<Course> getCourse(String qString) {
		return emf.createEntityManager().createQuery(qString, Course.class).getResultList();
	}
	public static java.util.List<Object> get(String qString) {
		return emf.createEntityManager().createQuery(qString, Object.class).getResultList();
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
	public static void insertUpdate( long type_id, long user_id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
	
		String q = "UPDATE Usr u SET u.typeid = " + type_id + " where u.id = " + user_id;
		trans.begin();
		try {
			em.createQuery(q).executeUpdate();
			trans.commit();
			// trans1.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();

		}
	}
}
