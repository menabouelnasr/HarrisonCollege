

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.DBUtil;
import model.Enroll;
import model.Usr;

/**
 * Servlet implementation class EnrollStudent
 */
@WebServlet("/EnrollStudent")
public class EnrollStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private String test(HttpServletRequest request) 
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	Long studID= (Long) request.getSession().getAttribute("studID");
    	System.out.println("Student ID " +studID);
		String courseID = request.getParameter("enrollID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String currSTART="",currEND="";
		Integer sID= (int) (long)studID;
		
		long start= Long.parseLong(startTime.substring(0,2)+startTime.substring(3));
		long end= Long.parseLong(endTime.substring(0,2)+endTime.substring(3));
		
		Course thisCourse = (Course) DBUtil.get("SELECT c FROM Course c WHERE c.id = " + courseID).get(0);
		
		
		if(DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID ).isEmpty())
		{
			Enroll u= new Enroll(sID, Integer.parseInt(courseID), "U");
			DBUtil.insert(u);
			return "first entry";
		}
		else
		{
			// for each course in this student's schedule...
			for (Object o1 : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID )) 
			{
				Enroll e = (Enroll)o1;
				
				// check if thisCourse conflicts with thisStudent's schedule
				Course currCourse = (Course) DBUtil.get("SELECT c FROM Course c WHERE c.id = " + e.getCourseid()).get(0);
				
				String qString = "SELECT t.time FROM Time t where t.id= '"+ currCourse.getTimeid() + "'";
		    	TypedQuery<String> q = em.createQuery(qString, String.class);
		    	currSTART= q.getSingleResult();
		    	long currStart= Long.parseLong(currSTART.substring(0,2)+currSTART.substring(3));
		    	
		    	
		    	String sString = "SELECT t.duration FROM Time t where t.id= '"+ currCourse.getTimeid() + "'";
		    	TypedQuery<String> s = em.createQuery(sString, String.class);
		    	currEND= s.getSingleResult();
		    	long currEnd= Long.parseLong(currEND.substring(0,2)+currEND.substring(3));
		    	
		    	
		    	if(start<=currEnd && currStart<=end)
				{
		    		System.out.println("Courses Overlap");
					return "Courses Overlap";
				}
		    	else
		    	{
		    		Enroll u= new Enroll(sID, Integer.parseInt(courseID), "U");
					DBUtil.insert(u);
		    		System.out.println("Course Enrolled");
		    		return "Course Enrolled";
		    	}
		    	
			}	
		}
		return "out";
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String output= test(request);
		System.out.println("Output: "+ output);
		request.setAttribute("display", output);
		getServletContext().getRequestDispatcher("/enrollstudent.jsp").forward(request, response);
	
		}
    
    	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}