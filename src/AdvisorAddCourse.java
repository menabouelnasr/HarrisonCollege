import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Classroom;
import model.Course;
import model.Enroll;

/**
 * Servlet implementation class AdvisorAddCourse
 */
@WebServlet("/AdvisorAddCourse")
public class AdvisorAddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvisorAddCourse() {
        super();
        // TODO Auto-generated constructor stub
    }  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String capacity= checkCapacity(request);
		int sid= (int) request.getSession().getAttribute("studID");;
		int cid  = Integer.parseInt(request.getParameter("courseid"));
		
		Enroll u= new Enroll(sid, cid, "U");
		StudentDB.insert(u);
	
		
	}
	 private String checkCapacity(HttpServletRequest request) 
	    {
	    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
	    	int studID= (int) request.getSession().getAttribute("studID");
	    	String courseID = request.getParameter("courseid");
	    	List<String> enrolledStudents;
	   
	    	int maxCap=0,student=0;
	    	
	    	String qString = "SELECT e FROM Enroll e WHERE e.courseid = " + courseID;
	    	TypedQuery<String> q = em.createQuery(qString, String.class);
	    	enrolledStudents= q.getResultList();
	    	student=enrolledStudents.size();
	    	System.out.println("Num of studs "+student);
	    	System.out.println(courseID);
	    	List<Course> course= DBUtil.getCourse("SELECT c FROM Course c WHERE c.id = " + courseID);
	    	for(Course temp:course)
	    	{
	    		System.out.println(temp.getName());
	    	}
	    	System.out.println("Course Size: "+course.size());
	    	System.out.println(" room id"+ course.get(0).getRoomid());
	    	Classroom room =DBUtil.getRoom("SELECT c FROM Classroom c WHERE c.roomnum ="+course.get(0).getRoomid());
	    	if(room==null)
	    		System.out.println("room is null");
	    	maxCap= Integer.parseInt(room.getMaxcap());
	    	System.out.println("Max Cap: "+maxCap);
	    	if(maxCap-student>0)
	    	{
	    		return "true";
	    	}
	    	return "false";
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
