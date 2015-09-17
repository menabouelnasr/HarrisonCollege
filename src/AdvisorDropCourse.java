import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Enroll;


import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import customTools.DBUtil;

/**
 * Servlet implementation class AdvisorDropCourse
 */
@WebServlet("/AdvisorDropCourse")
public class AdvisorDropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvisorDropCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String courseID = request.getParameter("courseId");
		String studID = request.getParameter("studId");
		int cID=0, sID=0;
		cID= Integer.parseInt(courseID);
		sID= Integer.parseInt(studID);
		System.out.println(courseID + " "+ studID);
		for (Object o : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + studID)) 
		{
			Enroll c = (Enroll)o;
			if(c.getCourseid()==cID && c.getStudid()==sID)
			{
				DBUtil.delete(c);
			}
		}
		
				
		getServletContext().getRequestDispatcher("/AddDropCourse").forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
