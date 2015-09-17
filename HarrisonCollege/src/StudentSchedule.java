

import java.io.IOException;

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
 * Servlet implementation class StudentSchedule
 */
@WebServlet("/StudentSchedule")
public class StudentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util.processUser(request);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String html="", startTime="", endTime="", day="", instructorID="";
		Long studID= (Long) request.getSession().getAttribute("studID");
		String finStart="", finEnd="";
		int newStart=0, newEnd=0;
		
		if(DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID).isEmpty())
		{
			html="You are not currently enrolled in any courses.";
		}
		else
		{
			
			for (Object o : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID)) 
			{
				Enroll c = (Enroll)o;
				Course n = (Course) DBUtil.get("SELECT c FROM Course c WHERE c.id = " + c.getCourseid()).get(0);
				
				String qString = "SELECT t.time FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> q = em.createQuery(qString, String.class);
		    	startTime= q.getSingleResult();
		    	
		    	if(Integer.parseInt(startTime.substring(0,2)+ startTime.substring(3))>1200)
		    	{
		    		newStart= Integer.parseInt(startTime.substring(0,2)+ startTime.substring(3))-11;
		    		finStart= Integer.toString(newStart)+ " pm";
		    	}
		    	else
		    	{
		    		finStart= startTime+ " am";
		    	}
		    	
		    	String sString = "SELECT t.duration FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> s = em.createQuery(sString, String.class);
		    	endTime= s.getSingleResult();
		    	
		    	if(Integer.parseInt(endTime.substring(0,2)+ endTime.substring(3))>1200)
		    	{
		    		newEnd= Integer.parseInt(endTime.substring(0,2)+ endTime.substring(3))-11;
		    		finEnd= Integer.toString(newEnd)+ " pm";
		    	}
		    	else
		    	{
		    		finEnd= endTime+ " am";
		    	}
		    	
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
				html += "<td>" +  finStart + "-"+ finEnd +"</td>";
				html += "<td>" + day + "</td>";
				html += "<td>" + n.getSubjectcode() + "</td>";
				html += "<td><a href=\"DropCourse?dropID=" + c.getCourseid() + "&studID="+request.getSession().getAttribute("studID")+"\">" + "x</a>" + "</td></tr>";	
			}
		}
		request.setAttribute("display", html);
		getServletContext().getRequestDispatcher("/studentschedule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
