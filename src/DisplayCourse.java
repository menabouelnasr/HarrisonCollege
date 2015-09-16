import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Course;
import model.Enroll;
import model.Student;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String currentc = "";
		int sid = Integer.parseInt(request.getParameter("Idd"));
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
	
			List<Enroll> currentcourse = StudentDB.selectbycurrentbyid(sid);
			System.out.println(currentcourse);
			currentc += "<div class=\"container\">";
			currentc += "<table class=\"table table=bordered\"><thead><th>Course Name</th><th>Credit</th></thead><tbody>";
			for (Enroll enroll : currentcourse) {
				System.out.println(1);
					Course s = StudentDB.getCourseByID(enroll.getCourseid());
					currentc += "<tr><td>" + s.getName() + "</td>";
					currentc += "<td>" + enroll.getGrade() + "</td>";
					currentc += "</tr>";

				// currentc += "<tr>";
				// currentc += "<td><a href=\"CourseDetail?Id="+c.getId()+"\">"
				// +c.getClass()+"</td><td>"+ c.getGrade()="N/A"+"</td>";
				// currentc += "</tr>";

				
			}
		currentc += "</tbody></table>";
		currentc += "</div>";
			request.setAttribute("currentc", currentc);
			getServletContext().getRequestDispatcher("/Display.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}