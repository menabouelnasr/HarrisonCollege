import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Enroll;
import model.Student;
import model.Util;

/**
 * Servlet implementation class viewGradesheet
 */
@WebServlet("/viewGradesheet")
public class viewGradesheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewGradesheet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String display =  getGradeEnrollList(request.getParameter("courseId"));
		request.setAttribute("tabledisplay", display);
		request.setAttribute("currentCourse", "<h2>Gradesheet</h2>");
		getServletContext().getRequestDispatcher("/coursedetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public static String getGradeEnrollList(String courseId) {
		String display="";
		List<Enroll> enroll_list = DBUtil.getEnroll("select e from Enroll e where e.courseid=" + courseId);
		if(enroll_list.isEmpty())
		{
			display= Util.failAlert("There are no students enrolled in this course.");
		}
		else
		{
			 display = "<style>" + ".bs-example{" + "margin-top:20%"
					+ "margin-left:20%" + "margin-bottom:20%" + "}" 
					+ "table { " + " table-layout: fixed;"
					+ " word-wrap: break-word;" + "}" + "</style>";
	
			display += "<table class=" + "\"table table-hover table-condensed\""
					+ "style=width:100%>";
			
			display += getGradeStudents(enroll_list, courseId);
		}
		
		return display;
		
	}

	private static String getGradeStudents(List<Enroll> enroll_list,String courseId) {
		String line="";
		line += "<tr>" + "<th><strong>" + "Student ID" + "</strong></th><br>"  
				+ "<th><strong>" + "Name" + "</strong></th><br>"
				+ "<th><strong>" + "Grade" + "</strong></th><br>"
				+ "<th><strong>" + "          " + "</strong></th><br>"
				+ "</tr>"
				;
		
		List<Student> students;
		for(Enroll enroll : enroll_list){
			
			students = DBUtil.getStudent("select s from Student s where s.id=" + enroll.getStudid());
		
				for(Student stud : students){
					
						line += "<tr>" 
							 + "<td>" + stud.getId() + "</td>"
							 + "<td>" + stud.getName() + "</td>"
							 + "<td>" +  enroll.getGrade() + "</td>"
						  	 +"</td>"
							 + "</tr>";
				}
			}
			
			line += "</table>";
			return line;
	
	}
}