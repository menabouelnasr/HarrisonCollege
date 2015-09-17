import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.Enroll;
import model.Student;

/**
 * Servlet implementation class CourseD
 */
@WebServlet("/CourseD")
public class CourseD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String currentg;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseD() {
        super();
        currentg = "";
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("Id"));
		currentg= "<table>";
		List <Enroll> currentgrade = StudentDB.selectbycurrent();
		Student student = StudentDB.selectstudentbyid(id);
		List<model.Course> course = StudentDB.selectcourse();

		List<Course> cs = new ArrayList<Course>();
			
		currentg += "<div><table class='table table-bordered table-striped'>";
		currentg += "<thead>";
		currentg += "<th>";
		currentg += "Course Name";
		currentg += "</th>";
		currentg += "<th>";
		currentg += "Semester";
		currentg += "</th>";
		currentg += "<th>";
		currentg += "Credit";
		currentg += "</th>";
		currentg += "<th>";
		currentg += "Grade";
		currentg += "</th>";
		currentg += "<th>";
		currentg += "</thead>";
		currentg += "<tbody>";
		for(Course c : cs){
			currentg += "<tr>"
			+"<td>"
			+c.getName()
			+"</td>"
			+"<td>"
			+c.getSemester()
			+"</td>"
			+"<td>"
			+c.getCredits()
			+"</td>"
			+"<td>"
			+currentgrade.getClass()
			+"</td>"
			+"</tr>";		
		}
		currentg += "</tbody>";
		currentg += "</table>";
		currentg += "</div>";
		request.setAttribute("currentg",currentg);
		getServletContext().getRequestDispatcher("DisplayCourse").forward(request,response);
		
	}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}