

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Instructor
 */

@WebServlet("/Instruct")
public class Instruct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Instruct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDropDownValue(request);
		getServletContext().getRequestDispatcher("/Professor.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request, response);
		getServletContext().getRequestDispatcher("/Professor.jsp").forward(request, response);
	
	}
		

	private void doProcess(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{
		
			String semester = request.getParameter("semester");
			String depId = request.getParameter("depName");
			String instructor = request.getParameter("instructor");
			String major = request.getParameter("major");
			String subject = request.getParameter("subject");
			String time = request.getParameter("time");
			
			
			System.out.println(semester + " "+ depId + " "+ instructor + " "+ major + " "+ subject +" "+time);

			String display = "<style>" + ".bs-example{" + "margin-top:20%"
								+ "margin-left:20%" + "margin-bottom:20%" + "}"

								+ "table { " + " table-layout: fixed;"
								+ " word-wrap: break-word;" + "}" + "</style>";

			display += "<table class=" + "\"table table-striped\""
						+ "style=width:100%>";

			display += "<tr>" + "<th><strong>" + "Course List" + "</strong></th><br>"  
						+ "</tr>"
						;
				
			
				//String usrId = request.getSession().getAttribute("userID").toString();
				
				String query = "SELECT c FROM Course c WHERE 1=1";
				System.out.println(!depId.equalsIgnoreCase("any"));
				if (!semester.equalsIgnoreCase("any")) {
					query += " AND c.semester = '" + semester + "\'";
				}
				
				if (!instructor.equalsIgnoreCase("any")) {
						query += " AND c.instructorid = " + instructor;
						
				}
				
				if (!depId.equalsIgnoreCase("any")) {
					query += " AND c.deptid = " + depId;	
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
					List<Time> time_list = DBUtil.getTime("Select t from Time t");
					
					for(Time tm: time_list){
						query += " AND c.deptid=" + tm.getId();
					}
		
				}
					
				List<Course> course_list = DBUtil.getCourse(query);
				display += getCourseLine(course_list);
				
				getDropDownValue(request);
				request.setAttribute("tabledisp", display);
				
				
		}	

		
private void getDropDownValue(HttpServletRequest request) {
		String semester = request.getParameter("semester");
		String depId = request.getParameter("depName");
		String instruct = request.getParameter("instructor");
		String major = request.getParameter("major");
		String sub = request.getParameter("subject");
		String tm = request.getParameter("time");
		
		System.out.println(semester + " "+ depId + " "+ instruct + " "+ major + " "+ sub +" "+tm);

			String html="";
			List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c");
			List<String> semesters=new ArrayList<String>();
			List<String> subject=new ArrayList<String>();
			for (int i=0; i<courses.size();i++)
			{
				if(!semesters.contains(courses.get(i).getSemester()))
				{
					semesters.add(courses.get(i).getSemester());
				}
				if(!subject.contains(courses.get(i).getSubjectcode()))
				{
					subject.add(courses.get(i).getSubjectcode());
				}
				
			}
			List<Department> department = DBUtil.getDepartment("SELECT d FROM Department d");	
			List<Instructor> instructor = DBUtil.getInstructor("SELECT i FROM Instructor i");
			
			List<Time> time = DBUtil.getTime("Select distinct t.day from Time t ");
			
			request.setAttribute("semesters", semesters);
			request.setAttribute("depName", department);
			request.setAttribute("subject", subject);
			request.setAttribute("major", department);
			request.setAttribute("instructor", instructor);
			request.setAttribute("time", time);
		
	}
		
	public String getCourseLine(List<Course> crse_list) {
		String line= "";
		
		for(Course course_list : crse_list){
			// display course name in JSP	
			System.out.println("$$");
			line += "<tr>" 
					+ "<td>" + "<a href= " + "\'" + "CourseDetail?courseId="+ course_list.getId()+ "\'" + ">"+ course_list.getName() + "</a>" + "</td>"
					+ "</tr>";
			
		}
		line += "</table>";
		System.out.println(line);
		return line;
		
	}

	
}
