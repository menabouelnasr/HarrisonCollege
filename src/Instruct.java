

import java.io.IOException;
import java.util.List;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Instructor
 * 
 * 
view their classes in the current semester
get the roster of students in their classes from this semester or previous semesters
assign grades to students in their classes
view grade sheets from previous semesters
view all course
view all classes in current semester
view all classes in a subject in current semester
view all classes by an instructor in a current semester
view all classes at a certain time in the current semester
view all courses in a department
view all current classes in a department
view all majors in a department

 */
@WebServlet("/Instruct")
public class Instruct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Usr> usr_list;
	


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
		
		String line = "";
		
		line += "<style>" + ".bs-example{" + "margin-top:20%"
				+ "margin-left:20%" + "margin-bottom:20%" + "}"

				+ "table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";

		line += "<table class=" + "\"table table-striped\""
				+ "style=width:100%>";

		line += "<tr>" + "<th>" + " <form  method='post' action='Instruct'>"
			 + "<select name='sel'>"
		     + "<option name='opt1' value='Course'>Course</option>"
		     + "<option value='Instructor'>Instructor</option>"
		     + "<option value='Department'>Department</option>"
		     + "<option value='Semester'>Semester</option>"
		     + "</select>" + "</th><br>" 
			
				;
		
		line +="<input type='Submit' value='Go'>"
			 + "</form>"
			 + "</tr>";
	
		request.setAttribute("message", line);
		getServletContext().getRequestDispatcher("/Professor.jsp").forward(request, response);
	}
		
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post");
		System.out.println(request.getParameter("Course"));
		System.out.println(request.getParameter("opt1"));
		
		String line = "<style>" + ".bs-example{" + "margin-top:20%"
		
				+ "margin-left:20%" + "margin-bottom:20%" + "}"

				+ "table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";

		line += "<table class=" + "\"table table-striped\""
				+ "style=width:100%>";

		// "drop down menu"
		//String usrId = request.getSession().getAttribute("userID").toString();
		
		
		//view all course
		List<Course> course = viewAllCourse();
		getCourseLine(course);
		
		
		// view all classes/courses in current semester
		List<Course> sem_crse  = viewSemCourse(request.getParameter("semester").toString());
		getCourseLine(sem_crse);
		
		
		
		//view all classes/courses in a subject in current semester
		List<Course> sub_sem = viewSubSem(request.getParameter("semester") , request.getParameter("subject"));
		getCourseLine(sub_sem);
		
		
		//view all classes by an instructor in a current semester
		List<Course> ins_sem = viewInsSem(request.getParameter("semester") , request.getParameter("Instructor"));
		getCourseLine(ins_sem);
		
		
		
		
		
		
		
		
		
		// select typeID from user id
//		String qString = "Select u from Usr u where u.id=" + usrId;
//		List<Object> ob_list = DBUtil.get(qString);
//		
//		for(Object o : ob_list){
//			Usr u = (Usr)o;
//			inst_id = u.getTypeid();
//		}
		
		// get department id for that instructor 
//		qString = "Select i from Instructor i where i.id=" + inst_id;
//		List<Object> ob_ins_list = DBUtil.get(qString);
//		
//		for(Object o : ob_ins_list){
//			Instructor i = (Instructor)o;
//			dept_id = i.getDeptid();
//		}
//		
//		// get all courses for that instructor id. 

//		qString = "Select c from Course where c.instructorid=" + inst_id;
//		List<Object> ob_crse_list = DBUtil.get(qString);
//		
//		Course c;
//		for(Object o : ob_crse_list){
//			c = (Course)o;
//		}
		
	}

	private List<Course> viewInsSem(String sem, String inst_name) {
		
		String sql = "Select i from Instructor i where i.name=" +inst_name;
		List<Object> inst_det = DBUtil.get(sql);
		List<Course> sem_sub_list = null;
		Instructor i;
		List<Instructor> inst_list = null;
		for(Object o : inst_det){
			i = (Instructor)o;
			inst_list.add(i);
		}
		
		for(Instructor Ins : inst_list){
			String qString = "Select c from Course c where c.semester = " +sem +" and c.instructorid=" + Ins.getId();
			List<Object> ob_all_course = DBUtil.get(qString);
			Course c = new Course();
			for(Object o : ob_all_course){
				c = (Course)o;
				sem_sub_list.add(c);
			}
		}
		
		return sem_sub_list;	
				
	}

	private List<Course> viewSubSem(String sem, String sub) {
		String qString = "Select c from Course c where c.semester = " +sem +" and c.name=" + sub;
		List<Object> ob_all_crse = DBUtil.get(qString);
		Course c = new Course();
		List<Course> sem_sub_list = null;
		for(Object o : ob_all_crse){
			c = (Course)o;
			sem_sub_list.add(c);
		}
		return sem_sub_list;	
				
		
	}

	public String getCourseLine(List<Course> crse_list) {
		String line= "";
		for(Course course_list : crse_list){
			// display course name in JSP
			line += "<tr>" + "<th>" + "Course List" + "</th><tr>";
			
			line += "<tr>" 
					+ "<td>" + course_list.getName()+ "<a style=margin-left:20% style=width:30% href= " + "\'" + "Course?courseId="+ course_list.getId()
					+ "\'" + ">"+ "</a>" + "</td>"
					+ "</tr>";
		}
		return line;
		
	}

	public List<Course> viewSemCourse(String sem) {
		String qString = "Select c from Course c where c.semester = " +sem;
		List<Object> ob_all_crse = DBUtil.get(qString);
		Course c = new Course();
		List<Course> sem_course_list = null;
		for(Object o : ob_all_crse){
			c = (Course)o;
			sem_course_list.add(c);
		}
		return sem_course_list;	
				
	}

	public List<Course> viewAllCourse() {
		String qString = "Select c from Course c ";
		List<Object> ob_all_crse = DBUtil.get(qString);
		Course c = new Course();
		List<Course> all_course_list = null;
		for(Object o : ob_all_crse){
			c = (Course)o;
			
			
		}
		return all_course_list;	
	}
}
