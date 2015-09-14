package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/NewDept")
public class NewDept extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/newDept.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String major  = request.getParameter("major");
		String name   = request.getParameter("name");
		
		Department d = new Department();
		d.setMajor(major);
		d.setName(name);
		DBUtil.insert(d);
		
		request.setAttribute("feedback", Util.successAlert("Course Updated!"));
		getServletContext().getRequestDispatcher("/admin/newDept.jsp").forward(request, response);
	}
	
}
