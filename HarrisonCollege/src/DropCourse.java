

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Enroll;
import model.Util;

/**
 * Servlet implementation class DropCourse
 */
@WebServlet("/DropCourse")
public class DropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		String courseID = request.getParameter("dropID");
		String studID = request.getParameter("studID");
		int cID=0, sID=0;
		cID= Integer.parseInt(courseID);
		sID= Integer.parseInt(studID);
		//System.out.println(courseID + " "+ studID);
		for (Object o : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID)) 
		{
			Enroll c = (Enroll)o;
			if(c.getCourseid()==cID && c.getStudid()==sID)
			{
				DBUtil.delete(c);
			}
		}
		getServletContext().getRequestDispatcher("/StudentSchedule").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
