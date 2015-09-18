package advisor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/AdvisorDropCourse")
public class AdvisorDropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);

		int cID = Integer.parseInt(request.getParameter("courseId"));
		int sID = Integer.parseInt(request.getParameter("studId"));
		for (Object o : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + sID + " AND e.courseid = " + cID )) {
			Enroll e = (Enroll)o;
			Course c = (Course) DBUtil.get("SELECT c FROM Course c WHERE c.id = " + cID);
			if(e.getCourseid() == cID && e.getStudid() == sID && c.getEnabled() == 1) {
				DBUtil.delete(e);
			}
		}
		getServletContext().getRequestDispatcher("/AddDropCourse").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
