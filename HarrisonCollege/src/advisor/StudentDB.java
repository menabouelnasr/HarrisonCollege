package advisor;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.*;

public class StudentDB {
	public static List<Enroll> select(){
		EntityManager em= DBUtil.getEmFactory().createEntityManager();
		String query="SELECT e FROM Enroll e";
		TypedQuery<Enroll> q = em.createQuery(query, Enroll.class);
		try{
			List<Enroll> grades = q.getResultList();
			return grades;
		} catch (Exception e){
			return null;
		}finally{
			em.close();
		}
	}
		public static List<model.Student> selectstudent(){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Student s";
			TypedQuery<Student> q = em.createQuery(query, Student.class);
			try{
				List<Student> students = q.getResultList();
				return students;
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}finally{
				em.close();
			}
	}
		
		
		public static Student selectstudentbyid(int id){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Student s where id =" + id;
			TypedQuery<Student> q = em.createQuery(query, Student.class);
			try{
				List<Student> students = q.getResultList();
				return students.get(0);
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}finally{
				em.close();
			}	
		}
		
		public static Course getCourseByID(long id){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Course s where s.id =" + id;
			System.out.println(query);
			TypedQuery<Course> q = em.createQuery(query, Course.class);
			
			try{
				List<Course> courses = q.getResultList();
				
				return courses.get(0);
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}finally{
				em.close();
			}	
		}
	

		public static List<Enroll> selectenrollbyid(int id){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Enroll s where s.studid = " + id;
			TypedQuery<Enroll> q = em.createQuery(query, Enroll.class);
			try{
				List<Enroll> lists = q.getResultList();
				return lists;
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}finally{
				em.close();
			}	
		}
		public static List<Course> selectcourse(){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Course s";
			TypedQuery<Course> q = em.createQuery(query, Course.class);
			try{
				List<Course> course = q.getResultList();
				return course;
			} catch (Exception e){
				return null;
			}finally{
				em.close();
			}					
	}		
		public static List<Course> selectcoursebyid(int id){
			EntityManager em= DBUtil.getEmFactory().createEntityManager();
			String query="SELECT s FROM Course s where s.id="+id;
			TypedQuery<Course> q = em.createQuery(query, Course.class);
			try{
				List<Course> course = q.getResultList();
				return course;
			} catch (Exception e){
				return null;
			}finally{
				em.close();
			}
		}
		
			public static List<Enroll> selectbycurrent(){
				EntityManager em= DBUtil.getEmFactory().createEntityManager();
				String query="SELECT s FROM Enroll s where s.grade= 'U'";
				System.out.println(query);
				TypedQuery<Enroll> q = em.createQuery(query, model.Enroll.class);
				try{
					List<Enroll> students = q.getResultList();
					return students;
				} catch (Exception e){
					e.printStackTrace();
					return null;
				}finally{
					em.close();
				}
		}
			
			public static List<Enroll> selectbycurrentbyid(int sid){
				EntityManager em= DBUtil.getEmFactory().createEntityManager();
				String query="SELECT s FROM Enroll s where s.grade = 'U' and s.studid = " + sid;
				System.out.println(query);
				TypedQuery<Enroll> q = em.createQuery(query, Enroll.class);
				try{
					List<Enroll> students = q.getResultList();
					return students;
				} catch (Exception e){
					e.printStackTrace();
					return null;
				}finally{
					em.close();
				}
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
			
		
	}