package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseID = request.getParameter("id");
		request.setAttribute("courseID", courseID);
		java.util.List<Object> list = DBUtil.get("SELECT c FROM Course c WHERE c.id = " + courseID);		
		request.setAttribute("list", list);
		
		long roomID = ((Course)list.get(0)).getRoomid();
		request.setAttribute("roomID", roomID);
		java.util.List<Object> room = DBUtil.get("SELECT c FROM Classroom c WHERE c.id = " + roomID);
		request.setAttribute("room", room);
		
		getServletContext().getRequestDispatcher("/admin/editCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type.equals("1")) {
			String courseID    = request.getParameter("courseID");
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
			
			for (Object o : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + courseID)) {
				Course c = (Course)o;
				
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
							
				DBUtil.update(c);
			}
		} else if (type.equals("2")) {
			//String room
		}
			
		request.setAttribute("feedback", Util.successAlert("Course Updated!"));
		getServletContext().getRequestDispatcher("/admin/editCourse.jsp").forward(request, response);
	}
	
}
