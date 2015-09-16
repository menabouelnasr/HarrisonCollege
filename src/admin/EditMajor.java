package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/EditMajor")
public class EditMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptID = request.getParameter("id");
		request.setAttribute("deptID", deptID);
		java.util.List<Object> list = DBUtil.get("SELECT d FROM Department d WHERE d.id = " + deptID);		
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin/editMajor.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptID = request.getParameter("deptID");
		String major  = request.getParameter("major");
		String name   = request.getParameter("name");
		
		for (Object o : DBUtil.get("SELECT d FROM Department d WHERE d.id = " + deptID)) {
			Department d = (Department)o;
			d.setMajor(major);
			d.setName(name);
			DBUtil.update(d);
		}
		
		request.setAttribute("feedback", Util.successAlert("Major Updated!"));
		getServletContext().getRequestDispatcher("/Admin").forward(request, response);
	}
	
}
