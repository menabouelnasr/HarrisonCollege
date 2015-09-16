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
		String currentc="";
		EntityManager em= DBUtil.getEmFactory().createEntityManager();		
		List<Enroll> currentcourse = StudentDB.selectbycurrent();
		currentc += "<div class=\"container\">";
		currentc += "<table class=\"table table=bordered\"><thead><th>Student Name</th><th>Major</th></thead><tbody>";
		for(model.Enroll enroll: currentcourse) {
			List<Course> course_list = StudentDB.selectcoursebyid((int)enroll.getCourseid());
			
			for(Course c : course_list){
			currentc += "<tr>" 
						+ "<td>" + "<a href= " + "\'" + "CourseD?courseId="+ c.getId()+ "\'" + ">"+ c.getName() + "</a>" + "</td>"
						+ "</tr>";			
			}			
//			currentc += "<tr>";
//			currentc += "<td><a href=\"CourseDetail?Id="+c.getId()+"\">" +c.getClass()+"</td><td>"+ c.getGrade()="N/A"+"</td>";
//			currentc += "</tr>";	
		
			currentc += "</tbody></table>";
			currentc += "</div>";
			request.setAttribute("currentc",currentc);
			getServletContext().getRequestDispatcher("/CurrentCourse.jsp").forward(request, response);
		
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}