package advisor;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class Advisor
 */
@WebServlet("/Advisor")
public class Advisor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String message;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advisor() {
        super();
        message = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);

		String message="";
		EntityManager em= DBUtil.getEmFactory().createEntityManager();
		try{
			List <model.Student> student = StudentDB.selectstudent();
			message += "<div class=\"container\">";
			message += "<table class=\"table table=bordered\"><thead><th>Student Name</th><th>Major</th></thead><tbody>";
			for(model.Student s: student) {
				
				message += "<tr>";
				message += "<td><a href=\"GradeDetail?Id="+s.getId()+"\">"
						+ s.getName()+"</td><td>"+ s.getMajor() +"</td>";
				message += "</tr>";	
			}
				message += "</tbody></table>";
				message += "</div>";
				
			request.setAttribute("message",message);
			getServletContext().getRequestDispatcher("/AdvisorTranscript.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}		
	}
}
