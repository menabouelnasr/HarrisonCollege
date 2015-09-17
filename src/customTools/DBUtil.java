package customTools; 
import javax.persistence.EntityManager; 
import javax.persistence.EntityManagerFactory; 
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence; 

import model.Classroom;
import model.Course;

public class DBUtil {
	
private static final EntityManagerFactory emf =
Persistence.createEntityManagerFactory("HarrisonCollege");

public static EntityManagerFactory getEmFactory() 
 {  
	 return emf; 
	 } 


	public static java.util.List<Course> getCourse(String qString) {
		return emf.createEntityManager().createQuery(qString, Course.class).getResultList();
	
 } 
	public static java.util.List<Object> get(String qString) {
		return emf.createEntityManager().createQuery(qString, Object.class).getResultList();
	}
	
	public static Classroom getRoom(String qString) {
		return emf.createEntityManager().createQuery(qString, Classroom.class).getSingleResult();
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
			em.close();}
		}

}

