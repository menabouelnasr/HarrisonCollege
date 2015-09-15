

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
import model.Time;

/**
 * Servlet implementation class DisplayCourse
 */
@WebServlet("/DisplayCourse")
public class DisplayCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String semester = request.getParameter("semester");
		String depName = request.getParameter("depName");
		String instructor = request.getParameter("instructor");
		String major = request.getParameter("major");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		String html="", startTime="", endTime=""; 
		String timeID="", instructorID="", day="";
		System.out.println(semester + " "+ depName + " "+ instructor + " "+ major + " "+ subject +" "+time);
		//if(!semester.equalsIgnoreCase("Any")&& depName.equalsIgnoreCase("Any")&&instructor.equalsIgnoreCase("Any")&&major.equalsIgnoreCase("Any")&&subject.equalsIgnoreCase("Any")&&time.equalsIgnoreCase("Any"))
		//{
			List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c where c.semester= '"+ semester +"'");
			
			for (Object o : courses) {
				Course n = (Course)o;
				
				String qString = "SELECT t.time FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> q = em.createQuery(qString, String.class);
		    	startTime= q.getSingleResult();
		    	
		    	String sString = "SELECT t.duration FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> s = em.createQuery(sString, String.class);
		    	endTime= s.getSingleResult();
		    	
		    	String tString = "SELECT t.day FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> t = em.createQuery(tString, String.class);
		    	day= t.getSingleResult();
		    	
		    	String mString = "SELECT i.name FROM Instructor i where i.id='"+ n.getInstructorid()+ "'"; //add instructor to DB                                                                                                                                  
		    	TypedQuery<String> m = em.createQuery(mString, String.class);
		    	instructorID= m.getSingleResult();

				html += "<tr><td>" + n.getName()+" "+ n.getCoursenum()+ "</td>";
				html += "<td>" + n.getSection() + "</td>";
				html += "<td>" + n.getDescription() + "</td>";
				html += "<td>" + n.getCredits()+ "</td>"; 
				html += "<td>" + instructorID + "</td>";
				html += "<td>" +  startTime + "-"+ endTime +"</td>";
				html += "<td>" + day + "</td>";
				html += "<td>" + n.getSubjectcode() + "</td>";
				html += "<td><a href=\"EnrollStudent?enrollID=" + n.getId() + "&startTime="+startTime+ "&endTime="+endTime+"\">" + "Enroll</a>" + "</td></tr>";
			} 
		
		//get rest from Ankushi
		request.setAttribute("display", html);
		getServletContext().getRequestDispatcher("/displaycourse.jsp").forward(request, response);
	}

}
