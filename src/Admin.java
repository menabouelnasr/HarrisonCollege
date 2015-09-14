

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//request.setAttribute("orders", showOrders(request));
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
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
