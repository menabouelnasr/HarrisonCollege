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
import model.Department;
import model.Instructor;
import model.Time;
import model.Util;

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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util.processUser(request);
		doProcess(request,response);
		
		//get rest from Ankushi
	}
	private void doProcess(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException
	{
		Util.processUser(request);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String semester = request.getParameter("semester");
		String depName = request.getParameter("depName");
		String instructor = request.getParameter("instructor");
		String major = request.getParameter("major");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		String html="", startTime="", endTime=""; 
		String instructorID="", day="";
		int output=0;
		String finStart="", finEnd="";
		int newStart=0, newEnd=0;
		
		//System.out.println(time);
		
		String query = "SELECT c FROM Course c WHERE 1=1 and c.enabled=1";
		
		if (!semester.equalsIgnoreCase("any")) {
			query += " AND c.semester = '" + semester + "\'";
		}
		
		if (!instructor.equalsIgnoreCase("any")) {
				//System.out.println(instructor);
				query += " AND c.instructorid = " + instructor;
				
		}
		
		if (!depName.equalsIgnoreCase("any")) {
			if(DBUtil.get("SELECT d.id FROM Department d where d.name='"+depName+"'").size()==0)
			{
				output= 1;
			}
			else
			{
			Long depId = (Long) DBUtil.get("SELECT d.id FROM Department d where d.name='"+depName+"'").get(0);
			query += " AND c.deptid = " + depId;	
			}
		}	
		

		if(!major.equalsIgnoreCase("any")){
			List<Department> dept_list = DBUtil.getDepartment("select d from Department d where d.major='" + major+"\'");
			for(Department dep :dept_list){
				query += " AND c.deptid=" +dep.getId();
			}
		}	
		
		if(!subject.equalsIgnoreCase("any")){
			query += " and c.subjectcode='" +subject + "\'";
		}

		if(!time.equalsIgnoreCase("any")){
			if(DBUtil.get("SELECT t.id FROM Time t where t.day='"+ time+"'").size()==0)
			{
				output= 1;
			}
			else
			{
				Long tID = (Long) DBUtil.get("SELECT t.id FROM Time t where t.day='"+ time+"'").get(0);
				query += " AND c.timeid=" + tID;
			}
			

		}
		List<Course> courses = DBUtil.getCourse(query);
		if(courses.isEmpty())
		{
			request.setAttribute("feedback", Util.failAlert("No courses are available for the entries you have selected."));
		}
		else
		{
		
			for (Object o : courses) 
			{
				Course n = (Course)o;
				
				String qString = "SELECT t.time FROM Time t where t.id= '"+ n.getTimeid() + "'";
		    	TypedQuery<String> q = em.createQuery(qString, String.class);
		    	startTime= q.getSingleResult();
		    	
		    	if(Integer.parseInt(startTime.substring(0,2)+ startTime.substring(3))>1200)
		    	{
		    		newStart= Integer.parseInt(startTime.substring(0,2)+ startTime.substring(3))-1200;
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
		    		newEnd= Integer.parseInt(endTime.substring(0,2)+ endTime.substring(3))-1200;
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
		    	instructorID= m.getResultList().get(0);
	
				html += "<tr><td>" + n.getName()+" "+ n.getCoursenum()+ "</td>";
				html += "<td>" + n.getSection() + "</td>";
				html += "<td>" + n.getDescription() + "</td>";
				html += "<td>" + n.getCredits()+ "</td>"; 
				html += "<td>" + instructorID + "</td>";
				html += "<td>" +  finStart + "-"+ finEnd +"</td>";
				html += "<td>" + day + "</td>";
				html += "<td>" + n.getSubjectcode() + "</td>";
				
				System.out.println(request.getSession().getAttribute("insID")+"    ^^^" +  request.getSession().getAttribute("adminID"));
				if(request.getSession().getAttribute("insID")==null && request.getSession().getAttribute("adminID")==null )
				{
					html += "<td><a href=\"EnrollStudent?enrollID=" + n.getId() + "&startTime="+startTime+ "&endTime="+endTime+"\">" + "Enroll</a>" + "</td></tr>";
				}
				
			} 
		}
		if(output>0)
		{
			request.setAttribute("feedback", Util.failAlert("One or more of your entries yielded invalid results. Search has been completed with available results."));
		}
		request.setAttribute("display", html);
		getServletContext().getRequestDispatcher("/displaycourse.jsp").forward(request, response);
	}

}
