package advisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class GradeDetail
 */
@WebServlet("/GradeDetail")
public class GradeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String grades;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeDetail() {
        super();
        grades = "";
        // TODO Auto-generated constructor stub
    }    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("Id"));
		grades= "<table>";
		List <Enroll> gradeList = StudentDB.selectenrollbyid(id);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		//Student student = StudentDB.selectstudentbyid(id);
		//List<model.Course> course = StudentDB.selectcourse();
		//System.out.println(student);
		List<Course> cs = new ArrayList<Course>();
		for(Enroll e : gradeList){
			Course c = StudentDB.getCourseByID((int) e.getCourseid());
			cs.add(c);
			map.put((int) c.getId(), e.getGrade());
		}
		Comparator<Course> courseComparator = new Comparator<Course>(){
			public int compare(Course c1, Course c2){
				return c2.getSemester().compareTo(c1.getSemester());
			}
		};
		
		Collections.sort(cs, courseComparator);			
		grades += "<div><table class='table table-bordered table-striped'>";
		grades += "<thead>";
		grades += "<th>";
		grades += "Subject Code";
		grades += "</th>";
		grades += "<th>";
		grades += "Course Number";
		grades += "</th>";
		grades += "<th>";
		grades += "Course Name";
		grades += "</th>";
		grades += "<th>";
		grades += "Semester";
		grades += "</th>";
		grades += "<th>";
		grades += "Credit";
		grades += "</th>";
		grades += "<th>";
		grades += "Grade";
		grades += "</th>";
		grades += "<th>";
		grades += "</thead>";
		grades += "<tbody>";
		for(Course c : cs){
			grades += "<tr>"
			+"<td>"
			+c.getSubjectcode()
			+"</td>"
			+"<td>"
			+c.getCoursenum()
			+"</td>"
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
			+ map.get((int) c.getId())
			+"</td>"
			+"</tr>";		
		}
		grades += "</tbody>";
		grades += "</table>";
		grades += "</div>";
		request.setAttribute("grades",grades);
		getServletContext().getRequestDispatcher("/AdvisorTranscript.jsp").forward(request,response);
	}
			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
