package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/NewMajor")
public class NewMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/newMajor.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String major  = request.getParameter("major");
		String name   = request.getParameter("name");
		
		Department d = new Department();
		d.setMajor(major);
		d.setName(name);
		DBUtil.insert(d);
		
		request.setAttribute("feedback", Util.successAlert("Major Added!"));
		getServletContext().getRequestDispatcher("/Admin").forward(request, response);
	}
}
