

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class viewStudent
 */
@WebServlet("/viewStudent")
public class viewStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String display = getEnrollList(request.getParameter("courseId"));
		request.setAttribute("tabledisplay", display);
		getServletContext().getRequestDispatcher("/coursedetails.jsp").forward(request, response);
		
	}

	public static String getEnrollList(String courseId) {
		List<Enroll> enroll_list = DBUtil.getEnroll("select e from Enroll e where e.courseid=" + courseId);
		
		String display = "<style>" + ".bs-example{" + "margin-top:20%"
				+ "margin-left:20%" + "margin-bottom:20%" + "}" 
				+ "table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";

		display += "<table class=" + "\"table table-striped\""
				+ "style=width:100%>";
		
		display += getStudents(enroll_list, courseId);
		
		return display;
		
	}

	public static String getStudents(List<Enroll> enroll_list, String courseID) {
		String line="";
		line += "<tr>" + "<th><strong>" + "Student ID" + "</strong></th><br>"  
				+ "<th><strong>" + "Name" + "</strong></th><br>"
				+ "<th><strong>" + "Major" + "</strong></th><br>"
				+ "<th><strong>" + "Entry Year" + "</strong></th><br>"
				+ "<th><strong>" + "GradYear" + "</strong></th><br>"
				+ "<th><strong>" + "          " + "</strong></th><br>"
				+ "</tr>"
				;
		
		System.out.println("enrolls    " + enroll_list.size());
		for(Enroll enroll : enroll_list){
			
			List<Student> students = DBUtil.getStudent("select s from Student s where s.id=" + enroll.getStudid());
				System.out.println("students   " + students.size());
				for(Student stud : students){
					
						line += "<tr>" 
							 + "<td>" + stud.getId() + "</td>"
							 + "<td>" + stud.getName() + "</td>"
							 + "<td>" + stud.getMajor() + "</td>"
							 + "<td>" + stud.getEntryyear() + "</td>"
							 + "<td>" + stud.getGradyear() + "</td>"
							 + "<td>" +				 
							 "<a  href= " + "\'" + "#" + "\'" + "onclick='showDiv(" + stud.getId() +")' " + ">"+  enroll.getGrade() + "</a>" + "</td>"
							 +"<td>"
							 +"<form class='form-inline' action='viewStudent' method='post' style='display:none;' id=" +stud.getId()
							 +">"
						  	 + "<input type='text' class='form-control input-sm' placeholder='Add Grade' name='newgrade'>"
							 + "<input type='hidden' name='studID' value="+ stud.getId() + ">"
							 + "<input type='hidden' name='courseID' value="+ courseID + ">"
						  	 +"	<input class='form-control input-sm' type='submit' name='sub' value='save'></input>"
						  	 
						  	 +"</form>"
						  	 +"</td>"
							 + "</tr>";
				}
			}
			
			line += "</table>";
			return line;
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(request.getParameter("studID"));
		int studID = Integer.valueOf(request.getParameter("studID"));
		String newgrade = request.getParameter("newgrade");
		int courseID = Integer.valueOf(request.getParameter("courseID"));
				
		
			Enroll e = (Enroll)DBUtil.get("select e from Enroll e where e.studid="+ studID + " and e.courseid=" +courseID).get(0);
			e.setGrade(newgrade);
			DBUtil.update(e);
		
		request.setAttribute("tabledisplay", successAlert("added successfully"));
		getServletContext().getRequestDispatcher("/coursedetails.jsp").forward(request, response);
		
		
	}
	
	public static String successAlert(String msg) {
		return "<div class=\"alert alert-success\"><strong>Success! </strong>" + msg + "</div>";
		
	}
}
