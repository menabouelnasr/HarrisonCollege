import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.DBUtil;
import model.Department;
import model.Instructor;
import model.Time;
import model.Util;

/**
 * Servlet implementation class InstructHome
 */
@WebServlet("/InstructHome")
public class InstructHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		HttpSession session = request.getSession();
		Long usrID = (long) session.getAttribute("insID");
		Integer userID= (int) (long)usrID;
		
		request.setAttribute("currentCourse", "<h2>Current Courses</h2>");
		request.setAttribute("previousCourse", "<h2>Previous Courses</h2>");
		request.setAttribute("tabledisplay", getCurrentSemDet(userID));
		request.setAttribute("secondtable", getPrevSemDet(userID));
		
		getServletContext().getRequestDispatcher("/coursedetails.jsp").forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	private String getPrevSemDet(int userID) {
		List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c where c.instructorid="+ userID + " and c.enabled= 0 order by c.semester DESC" );
		//List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c where c.instructorid=1 and c.enabled= 0 order by c.semester DESC");
		return getCourseHeader() +	getprevSemLine(courses); 
	}

	private String getprevSemLine(List<Course> courses) {
		String display = "";
		for(Course course_list : courses){
			
			display += "<tr>" 
					+ "<td>" + "<a href= " + "\'" + "viewGradesheet?courseId="+ course_list.getId()+ "\'" + ">"+ course_list.getName() + "</a>" + "</td>"
					+ "<td>" + course_list.getSemester() + "</td>"
					+ "</tr>";
			
		}
		display += "</table>";
		return display;
		
	}

	
	private String getCourseHeader() {
		String display = "<style>" + ".bs-example{" + "margin-top:20%"
				+ "margin-left:20%" + "margin-bottom:20%" + "}"

				+ "table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";

		display += "<table class=" + "\"table table-hover table-condensed\""
				+ "style=width:100%>";
		
		display += "<tr>"  
				+"<th><strong>" + "Gradesheet" + "</strong></th><br>"
				+"<th><strong>" + " Semester  " + "</strong></th><br>"
				+ "</tr>"
				;
		return display;
	}

	private String getCurrentSemDet(int userID) {
		List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c where c.instructorid="+ userID + " and c.semester='" +getCurrentSem() + "\'" );
		//List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c where c.instructorid=1" + " and c.semester='" +getCurrentSem() + "\'" );
		
		return getCourseHeader() +	getCourseLine(courses); 
		
	}

	private String getCourseLine(List<Course> courses) {
		String display = "";
		for(Course course_list : courses){
			
			display += "<tr>" 
					+ "<td>" + "<a href= " + "\'" + "viewGradesheet?courseId="+ course_list.getId()+ "\'" + ">"+ course_list.getName() + "</a>" + "</td>"
					+ "<td>" + course_list.getSemester() + "</td>"
					+ "<td>" + "<a href= " + "\'" + "viewStudent?courseId="+ course_list.getId()+ "\'" + ">"+ "View Students" + "</a>" + "</td>"
					+ "</tr>";
			
		}
		display += "</table>";
		return display;
		
	}

	public String getCurrentSem() {
		String currentSem = "";
		Calendar now = Calendar.getInstance();
		int currmonth = now.get(Calendar.MONTH) + 1;
		int currYear = now.get(Calendar.YEAR);
			if(currmonth>= 8 && currmonth<=12){
				currentSem = "Fall";
			}else if(currmonth>= 1 && currmonth<=5){
				currentSem = "Spring";
			} else if(currmonth>= 6 && currmonth<=7){
				currentSem = "Summer";
			}
		
		return currentSem + " " +currYear;
	}

	
	
}