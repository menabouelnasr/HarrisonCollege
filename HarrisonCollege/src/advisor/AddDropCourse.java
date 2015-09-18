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
 * Servlet implementation class AddDropCourse
 */
@WebServlet("/AddDropCourse")
public class AddDropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		
		String adddrop="";
		EntityManager em= DBUtil.getEmFactory().createEntityManager();	
		try{
			List <Student> student = StudentDB.selectstudent();
			adddrop += "<div class=\"container\">";
			adddrop += "<table class=\"table table=bordered\"><thead><th>Student Name</th><th>Major</th></thead><tbody>";
			for(Student s: student) {
				
				adddrop += "<tr>";
				adddrop += "<td><a href=\"AdvisorDisplayCourse?Idd=" + s.getId()+"\">"
						+ s.getName()+"</a></td><td>"+ s.getMajor() +"</td>";
				adddrop += "</tr>";	
			}
			
			adddrop += "</tbody></table>";
			adddrop += "</div>";
			
			request.setAttribute("adddrop",adddrop);
			getServletContext().getRequestDispatcher("/CurrentCourse.jsp").forward(request, response);

		}catch (Exception e){
			e.printStackTrace();
		}finally{
			em.close();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}	
	/*
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String currentc="";
	EntityManager em= DBUtil.getEmFactory().createEntityManager();		
	try{
	List<Enroll> currentcourse = StudentDB.selectbycurrent();
	System.out.println(currentcourse);
	currentc += "<div class=\"container\">";
	currentc += "<table class=\"table table=bordered\"><thead><th>Student Name</th><th>Credit</th></thead><tbody>";
	for(model.Enroll enroll: currentcourse) {
		List<Course> course_list = StudentDB.selectcoursebyid((int)enroll.getCourseid());
		
		for(Course c : course_list){
			System.out.println(c.getId());
			currentc += "<tr>" 
					 + "<td><a href= " + "\'" + "CourseD?courseId="+ c.getId()+ "\">" +c.getName()+"</a>"+c.getCredits()+"<tr>"+"</td>";
			currentc += "</tr>";		

		}			
//		currentc += "<tr>";
//		currentc += "<td><a href=\"CourseDetail?Id="+c.getId()+"\">" +c.getClass()+"</td><td>"+ c.getGrade()="N/A"+"</td>";
//		currentc += "</tr>";	
	
		currentc += "</tbody></table>";
		currentc += "</div>";
		request.setAttribute("currentc",currentc);
		getServletContext().getRequestDispatcher("/CurrentCourse.jsp").forward(request, response);

	}}catch (Exception e){
		e.printStackTrace();
	}finally{
		em.close();}
	}	
	*/
	}
		
