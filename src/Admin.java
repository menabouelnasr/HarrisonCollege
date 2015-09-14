import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//Util.processUser(request, false);
		request.setAttribute("courses", listCourses(request));
		request.setAttribute("rooms",   listRooms(request));
		request.setAttribute("depts",   listDepts(request));
		request.setAttribute("majors",  listMajors(request));
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
	}
	
	private String listCourses(HttpServletRequest request) {
		String html = "";
		System.out.println("WHAT THE FUCK");
		for (Object o : DBUtil.get("SELECT c FROM Course c")) {
			System.out.println("WHAT THE FUCK222");
			Course c = (Course)o;
			html += "<tr><td>" + c.getId() + "</td>";
			html += "<td>" + c.getEnabled() + "</td>";
			html += "<td>" + c.getDeptid() + "</td>";
			html += "<td>" + c.getSubjectcode() + "</td>";
			html += "<td>" + c.getCoursenum() + "</td>";
			html += "<td>" + c.getSection() + "</td>";
			html += "<td>" + c.getTimeid() + "</td>";
			html += "<td>" + c.getInstructorid() + "</td>";
			html += "<td>" + c.getRoomid() + "</td>";
			html += "<td>" + c.getName() + "</td>";
			html += "<td>" + c.getDescription()+ "</td>";
			html += "<td>" + c.getCredits() + "</td>";
			html += "<td>" + c.getSemester() + "</td></tr>";
		}		
		return html;
	}
	
	private String listRooms(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT c FROM Classroom c")) {
			Classroom c = (Classroom)o;
			html += "<tr><td>" + c.getId() + "</td>";
			html += "<td>" + c.getBldgname() + "</td>";
			html += "<td>" + c.getRoomnum() + "</td>";
			html += "<td>" + c.getMaxcap() + "</td></tr>";
		}		
		return html;
	}
	
	private String listDepts(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT d FROM Department d")) {
			Department d = (Department)o;
			html += "<tr><td>" + d.getId() + "</td>";
			html += "<td>" + d.getName() + "</td>";
			html += "<td>" + d.getMajor() + "</td></tr>";
		}		
		return html;
	}

	private String listMajors(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT d FROM Department d")) {
			Department d = (Department)o;
			html += "<tr><td>" + d.getId() + "</td>";
			html += "<td>" + d.getName() + "</td>";
			html += "<td>" + d.getMajor() + "</td></tr>";
		}		
		return html;
	}
	
	/*
Create, update, list or disable a course
Create, update, list or disable a classroom
Create, update, list or disable a department
Create, update, list or disable a major
Add class to schedule for current or later semester
Remove class from schedule for current or later semester
Change a new users type to (student, instructor, advisor or administrator)
Override maximum enrollment hold
view all course
view all classes by an instructor
view all classes by a student
view a list of all students taught by an instructor
view a list of all instructors that have taught a class
view a list of all classes that an instructor has taught
view a list of all classes of a course
view a list of all classrooms used by a course
view a list of all classrooms used by an instructor
view a list of all classrooms used by a student
view all classes in current semester
view all classes in a subject in current semester
view all classes by an instructor in a current semester
view all classes at a certain time in the current semester
view all courses in a department
view all current classes in a department
view all majors in a department
	 */
	
	
}
