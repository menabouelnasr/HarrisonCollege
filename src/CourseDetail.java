

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.Department;
import model.Instructor;
import model.Time;

/**
 * Servlet implementation class CourseDetail
 */
@WebServlet("/CourseDetail")
public class CourseDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String finStart="", finEnd="",  startTime="", endTime="";
		int newStart=0, newEnd=0;
		
		
		List<Course> cousr_list = DBUtil.getCourse("select c from Course c where c.id=" + request.getParameter("courseId"));
		String display = "<style> table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";

		display += "<table class=" + "\"table table-striped\""  
				+ "style=width:100%>";

		display += "<tr>" + "<th><strong>" + "Course Name" + "</strong></th><br>"  
				+ "<th><strong>" + "Description" + "</strong></th><br>"
				+ "<th><strong>" + "Semester" + "</strong></th><br>"
				+ "<th><strong>" + "Credits" + "</strong></th><br>"
				+ "<th><strong>" + "Enabled" + "</strong></th><br>"
				+ "<th><strong>" + "Subject Code" + "</strong></th><br>"
				+ "<th><strong>" + "Course number" + "</strong></th><br>"
				+ "<th><strong>" + "Instructor" + "</strong></th><br>"
				+ "<th><strong>" + "Department" + "</strong></th><br>"
				+ "<th><strong>" + "Time" + "</strong></th><br>"
				+ "</tr>"
				;
		
		String enable="No";
		for(Course course : cousr_list){
			if(course.getEnabled()==1){
				enable="Yes";
			}
			List<Instructor> instructor = DBUtil.getInstructor("SELECT i FROM Instructor i where i.id=" + course.getInstructorid());
			List<Department> department = DBUtil.getDepartment("SELECT d FROM Department d where d.id=" +course.getDeptid());
			List<Time> time_list = DBUtil.getTime("Select t from Time t where t.id=" +course.getTimeid());
			
		
			display += "<tr>" 
					+ "<td>" + course.getName() + "</td>" 
					+ "<td>" + course.getDescription()+ "</td>"
					+ "<td>" + course.getSemester()+ "</td>"
					+ "<td>" + course.getCredits()+ "</td>"
					+ "<td>" + enable+ "</td>"
					+ "<td>" + course.getSubjectcode()+ "</td>"
					+ "<td>" + course.getCoursenum()+ "</td>"
					+ "<td>" + instructor.get(0).getName()+ "</td>"
					+ "<td>" + department.get(0).getName()+ "</td>"
					+ "<td>" + time_list.get(0).getDay() + "  " + time_list.get(0).getTime() + "</td>"
					+ "</tr>";
		}
		
		display +="</table>";
		request.setAttribute("tabledisplay", display);
				
		getServletContext().getRequestDispatcher("/coursedetails.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
