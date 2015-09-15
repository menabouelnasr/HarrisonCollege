package admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.*;
import model.*;

@WebServlet("/EditDept")
public class EditDept extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptName = request.getParameter("name");
		request.setAttribute("oldName", deptName);
		//java.util.List<Object> list = DBUtil.get("SELECT d FROM Department d WHERE d.id = " + deptID);		
		//request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin/editDept.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldName = request.getParameter("oldName");
		String newName = request.getParameter("newName");
		
		for (Object o : DBUtil.get("SELECT d FROM Department d WHERE d.name = '" + oldName + "'")) {
			Department d = (Department)o;
			d.setName(newName);
			DBUtil.update(d);
		}
		
		request.setAttribute("feedback", Util.successAlert("Course Updated!"));
		getServletContext().getRequestDispatcher("/admin/editDept.jsp").forward(request, response);
	}
	
}
