package admin;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/NewRoom")
public class NewRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		getServletContext().getRequestDispatcher("/admin/newRoom.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		String bldgname = request.getParameter("bldgname");
		String maxcap   = request.getParameter("maxcap");
		String roomnum  = request.getParameter("roomnum");
	
		Classroom c =  new Classroom();
		c.setBldgname(bldgname);
		c.setMaxcap(maxcap);
		c.setRoomnum(roomnum);
		DBUtil.insert(c);
		
		request.setAttribute("feedback", Util.successAlert("Classroom Added!"));
		getServletContext().getRequestDispatcher("/Admin").forward(request, response);
	}
	
}