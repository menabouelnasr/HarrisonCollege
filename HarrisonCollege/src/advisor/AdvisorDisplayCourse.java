package advisor;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class DisplayCourse
 */
@WebServlet("/AdvisorDisplayCourse")
public class AdvisorDisplayCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String currentc = "";
		System.out.println("ID: "+request.getParameter("Idd"));
		int sid = Integer.parseInt(request.getParameter("Idd"));
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
	
			List<Enroll> currentcourse = StudentDB.selectbycurrentbyid(sid);
			System.out.println(currentcourse);
			currentc += "<div class=\"container\">";
			currentc += "<table class=\"table table=bordered\"><thead><th>Course Name</th><th>Grade</th><th>Credit</th><th>Drop</th></thead><tbody>";
			for (Enroll enroll : currentcourse) {
					Course s = StudentDB.getCourseByID(enroll.getCourseid());
					currentc += "<tr><td>" + s.getName() + "</td>";
					currentc += "<td>" + enroll.getGrade() + "</td>";
					currentc += "<td>" + s.getCredits()+ "</td>";
					currentc += "<td><a href = \"AdvisorDropCourse?studId=" + sid + "&courseId=" + enroll.getCourseid() + "\">drop</a></td>";
					currentc += "</tr>";

				// currentc += "<tr>";
				// currentc += "<td><a href=\"CourseDetail?Id="+c.getId()+"\">"
				// +c.getClass()+"</td><td>"+ c.getGrade()="N/A"+"</td>";
				// currentc += "</tr>";
						
				
			}
		currentc += "</tbody></table>";
		currentc += "</div>";
		System.out.println("Student ID: "+ sid);
		request.getSession().setAttribute("studID", sid);
			request.setAttribute("currentc", currentc);
			request.setAttribute("sid", sid);
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