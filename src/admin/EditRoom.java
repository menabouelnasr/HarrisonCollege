package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/EditRoom")
public class EditRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID = request.getParameter("id");
		request.setAttribute("roomID", roomID);
		java.util.List<Object> list = DBUtil.get("SELECT c FROM Classroom c WHERE c.id = " + roomID);		
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin/editRoom.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomID   = request.getParameter("roomID");
		String bldgname = request.getParameter("bldgname");
		String maxcap   = request.getParameter("maxcap");
		String roomnum  = request.getParameter("roomnum");
		
		for (Object o : DBUtil.get("SELECT c FROM Classroom c WHERE c.id = " + roomID)) {
			Classroom c = (Classroom)o;
			c.setBldgname(bldgname);
			c.setMaxcap(maxcap);
			c.setRoomnum(roomnum);
			DBUtil.update(c);
		}
		
		request.setAttribute("feedback", Util.successAlert("Course Updated!"));
		getServletContext().getRequestDispatcher("/admin/editRoom.jsp").forward(request, response);
	}
	
}
