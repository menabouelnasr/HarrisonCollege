package source;
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
		removeCourse(request.getParameter("deleteCourseID"));
		removeRoom(request.getParameter("deleteRoomID"));
		removeDept(request.getParameter("deleteDeptID"));
		
		request.setAttribute("courses", listCourses(request));
		request.setAttribute("rooms",   listRooms(request));
		request.setAttribute("depts",   listDepts(request));
		request.setAttribute("majors",  listMajors(request));
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
	}
	
	private String listCourses(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT c FROM Course c")) {
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
			html += "<td>" + c.getSemester() + "</td>";
			html += "<td><a href=\"EditCourse?id=" + c.getId() + "\">Edit</a></td>";
			html += "<td><a href=\"Admin?deleteCourseID=" + c.getId() + "\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a></td><tr/>";
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
			html += "<td>" + c.getMaxcap() + "</td>";
			html += "<td><a href=\"EditRoom?id=" + c.getId() + "\">Edit</a></td>";
			html += "<td><a href=\"Admin?deleteRoomID=" + c.getId() + "\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a></td><tr/>";
		}		
		return html;
	}
	
	private String listDepts(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT d FROM Department d")) {
			Department d = (Department)o;
			html += "<tr><td>" + d.getId() + "</td>";
			html += "<td>" + d.getName() + "</td>";
			html += "<td>" + d.getMajor() + "</td>";
			html += "<td><a href=\"EditDept?id=" + d.getId() + "\">Edit</a></td>";
			html += "<td><a href=\"Admin?deleteDeptID=" + d.getId() + "\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a></td><tr/>";
		}		
		return html;
	}

	private String listMajors(HttpServletRequest request) {
		String html = "";
		for (Object o : DBUtil.get("SELECT d FROM Department d")) {
			Department d = (Department)o;
			html += "<tr><td>" + d.getId() + "</td>";
			html += "<td>" + d.getName() + "</td>";
			html += "<td>" + d.getMajor() + "</td>";
			html += "<td><a href=\"EditMajor?id=" + d.getId() + "\">Edit</a></td>";
			html += "<td><a href=\"Admin?deleteDeptID=" + d.getId() + "\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a></td><tr/>";
		}		
		return html;
	}
	
	private void removeCourse(String courseID) {
		for (Object o : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + courseID))
			DBUtil.delete(o);
	}
	private void removeRoom(String roomID) {
		for (Object o : DBUtil.get("SELECT c FROM Classroom c WHERE c.id = " + roomID))
			DBUtil.delete(o);
	}
	private void removeDept(String deptID) {
		for (Object o : DBUtil.get("SELECT d FROM Department d WHERE d.id = " + deptID))
			DBUtil.delete(o);
	}
	
	/*
Create, update, list or disable a course
Create, update, list or disable a classroom
Create, update, list or disable a department
Create, update, list or disable a major
Add class to schedule for current or later semester
Remove class from schedule for current or later semester
Change a new users type to (student, instructor, advisor or administrator)
Override maximum enrollment hold // gonna need to make new room wtf
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
