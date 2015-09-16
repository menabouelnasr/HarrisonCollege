import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class AddDropCourse
 */
@WebServlet("/AddDropCourse")
public class AddDropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	private String adddrop;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDropCourse() {
        super();
        adddrop="";
        // TODO Auto-generated constructor stub
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
		String adddrop="";
		EntityManager em= DBUtil.getEmFactory().createEntityManager();	
		try{
			List <model.Student> student = StudentDB.selectstudent();
			adddrop += "<div class=\"container\">";
			adddrop += "<table class=\"table table=bordered\"><thead><th>Student Name</th><th>Major</th></thead><tbody>";
			for(model.Student s: student) {
				
				adddrop += "<tr>";
				adddrop += "<td><a href=\"DisplayCourse?Id="+s.getId()+"\">"
						+ s.getName()+"</td><td>"+ s.getMajor() +"</td>";
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

	}
	
	
