package admin;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/NewCourse")
public class NewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		getServletContext().getRequestDispatcher("/admin/newCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		String enabled     = request.getParameter("enabled");
		String deptID      = request.getParameter("deptid");
		String subjectCode = request.getParameter("subjectcode");
		String courseNum   = request.getParameter("coursenum");
		String section     = request.getParameter("section");
		String timeID      = request.getParameter("timeid");
		String instID      = request.getParameter("instructorid");
		String roomID      = request.getParameter("roomid");
		String name        = request.getParameter("name");
		String desc        = request.getParameter("description");
		String credits     = request.getParameter("credits");
		String semester    = request.getParameter("semester");

		Course c = new Course();
		c.setEnabled(Long.parseLong(enabled));
		c.setDeptid(Long.parseLong(deptID));
		c.setSubjectcode(subjectCode);
		c.setCoursenum(courseNum);
		c.setSection(section);
		c.setTimeid(Long.parseLong(timeID));
		c.setInstructorid(Long.parseLong(instID));
		c.setRoomid(Long.parseLong(roomID));
		c.setName(name);
		c.setDescription(desc);
		c.setCredits(credits);
		c.setSemester(semester);	
		System.out.println("ID OF COURSE BEING ADDED: " + c.getId());
		DBUtil.insert(c);
		System.out.println("ID OF COURSE ADDED: " + c.getId());
		
		request.setAttribute("feedback", Util.successAlert("Course Added!"));
		getServletContext().getRequestDispatcher("/Admin").forward(request, response);
	}
	
}
