import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException { }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Util.processUser(request);
		List<Department> department = DBUtil.getDepartment("SELECT d FROM Department d");
		List<String> major=new ArrayList<String>();
		for (int i=0; i<department.size();i++)
		{

			if(!major.contains(department.get(i).getMajor()))
			{
				major.add(department.get(i).getMajor());
			}
		}
		request.setAttribute("major", major);
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		doProcess(request, response);
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	public void destroy() { }
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		// Get From Form
		String type     = request.getParameter("type");
		String email    = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String major    = request.getParameter("major");
		String entryYear= request.getParameter("entry");
		String gradYear = request.getParameter("grad");
		String studType="";
		
		if (type == null || type.equals("")) {
			// do nothing
		} else if (type.equals("1")) {
			if (signup(email, username, password, 0, major,entryYear,gradYear, request)) {
				request.setAttribute("feedback", Util.successAlert("User created succcessfully!")); 
			} else {
				request.setAttribute("feedback", Util.failAlert("Invalid data entered.")); 
			}
		} else if (type.equals("2")) {
			long id = login(username, password);
			if (id == -1) {
				request.setAttribute("feedback", Util.failAlert("Wrong username/password.")); 
			} else {
				request.getSession().setAttribute("userID", id);
				request.setAttribute("feedback", Util.successAlert("Log In Successful!")); 
				System.out.println("USERID: " + request.getSession().getAttribute("userID"));
				
				String sString = "SELECT u.type FROM Usr u where u.id= '"+ request.getSession().getAttribute("userID") + "'";
		    	TypedQuery<String> s = em.createQuery(sString, String.class);
		    	studType= s.getSingleResult();
		    	System.out.println(studType);
		    	System.out.println("student id: "+id);
		    	if(studType.equalsIgnoreCase("student"))
		    	{
		    		request.getSession().setAttribute("studID", id);
		    	}
			}
		}
		
		request.setAttribute("navRight", Util.checkLogin(request)); 
	}

	
	private boolean signup(String email, String username, String password, int type, String major, String entryYear, String gradYear, HttpServletRequest request) {
		
		if (hasNull(email, username, password)) 
		{ return false; 
		}
		else
		{
			Usr u= new Usr(email, username, password, "student", 1,"0");
			DBUtil.insert(u);
			createStudent(username,major, entryYear, gradYear, request);
		}
		return true;
	}
	private boolean createStudent(String username,String major, String entryYear, String gradYear, HttpServletRequest request) 
	{	EntityManager em = DBUtil.getEmFactory().createEntityManager();
		long userID=0, ID = 0;
		
		DBUtil.insert(new Student(username, major,entryYear,gradYear));
		
		String qString = "Select max(s.id) from Student s";
    	TypedQuery<Long> q = em.createQuery(qString, Long.class);
    	ID= q.getSingleResult();
    	System.out.println("Student ID: "+ID);
    	
    	request.getSession().setAttribute("studID", ID);
    	
    	
    	String mString = "Select max(u.id) from Usr u";
    	TypedQuery<Long> m = em.createQuery(mString, Long.class);
    	userID= m.getSingleResult();
    	System.out.println(userID);
    	
    	DBUtil.insertUpdate(ID, userID);
		return true;
	}
	private long login(String username, String password) {
		if (hasNull("x", username, password)) { return -1; }
		
		for (Object o : DBUtil.get("SELECT u FROM Usr u WHERE u.username = '" + username + "'")) {
			Usr u = (Usr)o;
			if (u.getPassword().equals(password)) {
				return u.getId();
			}
		}
		return -1;
	}	
	
	private boolean hasNull(String email, String username, String password) {
		if (   email == null || email.equals(""))    { return true; }
		if (username == null || username.equals("")) { return true; }
		if (password == null || password.equals("")) { return true; }
		return false;
	}
}

