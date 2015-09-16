

import java.io.IOException;
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
import model.Util;

/**
 * Servlet implementation class Transcript
 */
@WebServlet("/Transcript")
public class Transcript extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transcript() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	Util.processUser(request);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Long studID=(Long) request.getSession().getAttribute("studID");
		String startTime="", endTime="", day="", html="", instructorID="";
	
		for (Object o : DBUtil.get("SELECT e FROM Enroll e where e.studid="+ studID))
		{
			Enroll c = (Enroll)o;
			Course n = (Course) DBUtil.get("SELECT c FROM Course c where c.id="+ c.getCourseid()).get(0);
			
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
	    	html += "<tr><td>" + n.getSemester() + "</td>";
			html += "<td>" + n.getName()+" "+ n.getCoursenum()+ "</td>";
			html += "<td>" + n.getSection() + "</td>";
			html += "<td>" + n.getCredits()+ "</td>"; 
			html += "<td>" + instructorID + "</td>";
			html += "<td>" + n.getSubjectcode() + "</td>";
			html += "<td>" + c.getGrade() + "</td></tr>";
			//html += "<td><a href=\"DropCourse?dropID=" + c.getCourseid() + "&studID="+request.getSession().getAttribute("studID")+"\">" + "x</a>" + "</td></tr>";	
		}
		request.setAttribute("display", html);
		getServletContext().getRequestDispatcher("/transcript.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Long studID=(Long) request.getSession().getAttribute("studID");
		String startTime="", endTime="", day="", html="", instructorID="";
	
		for (Object o : DBUtil.get("SELECT e FROM Enroll e where e.studid="+ studID))
		{
			Enroll c = (Enroll)o;
			Course n = (Course) DBUtil.get("SELECT c FROM Course c where c.id="+ c.getCourseid()).get(0);
			
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
	    	html += "<tr><td>" + n.getSemester() + "</td>";
			html += "<td>" + n.getName()+" "+ n.getCoursenum()+ "</td>";
			html += "<td>" + n.getSection() + "</td>";
			html += "<td>" + n.getCredits()+ "</td>"; 
			html += "<td>" + instructorID + "</td>";
			html += "<td>" + n.getSubjectcode() + "</td>";
			html += "<td>" + c.getGrade() + "</td></tr>";
			//html += "<td><a href=\"DropCourse?dropID=" + c.getCourseid() + "&studID="+request.getSession().getAttribute("studID")+"\">" + "x</a>" + "</td></tr>";	
		}
		request.setAttribute("feedback", Util.successAlert("Transcript Purchased: A $5 charge will be added to your tutiton statement."));
		request.setAttribute("display", html);
		getServletContext().getRequestDispatcher("/transpurchase.jsp").forward(request, response);
	}

}
