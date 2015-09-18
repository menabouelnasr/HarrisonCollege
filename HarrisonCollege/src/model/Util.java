package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.*;

public class Util {
	
	private static final String loginButtons = 
	        "<li><a href=\"login\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>" + 
	        "<li><a href=\"login\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>";
	
	public static String checkLogin(HttpServletRequest request) {
		return checkLogin(request, false);
	}
	
	public static String checkLogin(HttpServletRequest request, boolean here) {
		Long userID = (Long) request.getSession().getAttribute("userID");

		if (userID == null || userID == -1) {
			return loginButtons;
		} else {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "SELECT u FROM Usr u WHERE u.typeid = " + userID;
			List<Usr> users = em.createQuery(qString, Usr.class).getResultList();
			for (Usr u : users) {
				if(u.getType().equalsIgnoreCase("student"))
				{
					return formatSignOutButtons(u.getId(), u.getUsername(), here, false);
				}
				else if(u.getType().equalsIgnoreCase("instructor"))
				{
					return formatInstructorButtons(u.getId(), u.getUsername(), here, false);
				}
				else if(u.getType().equalsIgnoreCase("admin"))
				{
					return formatAdminButtons(u.getId(), u.getUsername(), here, true);
				}
				else if (u.getType().equalsIgnoreCase("advisor")) {
					return formatAdvisorButtons(u.getId(), u.getUsername(), here, true);
				}
			}
			return loginButtons;
		}
	}
	
	private static boolean isAdmin(long userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM Usr u WHERE u.type = 'admin' AND u.id =" + userID; 
		List<Usr> users = em.createQuery(qString, Usr.class).getResultList();
		for (Usr u : users) {
			return true;
		}
		return false;
	}
	
	private static String formatSignOutButtons(long userID, String userName, boolean here, boolean isAdmin) {
		String select = "";
		if (here) {
			select = " class=\"active\"";
		}
		String admin  = "<li><a href=\"admin\"><span class=\"glyphicon glyphicon-cog\"></span> Admin</a></li>";
        //String acc    = "<li" + select + "><a href=\"CartProcess?userID=" + userID + "\"><span class=\"glyphicon glyphicon-user\"></span> " + userName + "'s Cart</a></li>";
       
        
        String acc = "<li class=\"dropdown\">" + 
        "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> " + (userName.substring(0,1).toUpperCase()+ userName.substring(1)) + "'s Profile<span class=\"caret\"></span></a>" +
	    "<ul class=\"dropdown-menu\">" + 
        "<li><a href=\"StudentSchedule\">My Schedule</a></li>" + 
        "<li><a href=\"Transcript\">My Transcript</a></li>" + 
	    "<li><a href=\"CourseList\">Course Lookup</a></li>" + 
	    "</ul></li>";
        
        String logOut = "<li><a href=\"login?logout=true\"><span class=\"glyphicon glyphicon-log-in\"></span> Sign Out</a></li>";
        if (!isAdmin) { admin = ""; }
		return admin + acc + logOut;
	}
	
	private static String formatInstructorButtons(long userID, String userName, boolean here, boolean isAdmin) {
		String select = "";
		if (here) {
			select = " class=\"active\"";
		}
		String admin  = "<li><a href=\"admin\"><span class=\"glyphicon glyphicon-cog\"></span> Admin</a></li>";
        //String acc    = "<li" + select + "><a href=\"CartProcess?userID=" + userID + "\"><span class=\"glyphicon glyphicon-user\"></span> " + userName + "'s Cart</a></li>";
       
        
        String acc = "<li class=\"dropdown\">" + 
        "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> " + (userName.substring(0,1).toUpperCase()+ userName.substring(1)) + "'s Profile<span class=\"caret\"></span></a>" +
	    "<ul class=\"dropdown-menu\">" + 
        "<li><a href=\"InstructHome\">My Class History</a></li>" + 
	    "<li><a href=\"CourseList\">Course Lookup</a></li>" + 
	    "</ul></li>";
        
        String logOut = "<li><a href=\"login?logout=true\"><span class=\"glyphicon glyphicon-log-in\"></span> Sign Out</a></li>";
        if (!isAdmin) { admin = ""; }
		return admin + acc + logOut;
	}
	
	private static String formatAdminButtons(long userID, String userName, boolean here, boolean isAdmin) {
		String select = "";
		if (here) {
			select = " class=\"active\"";
		}
		
        String acc = "<li class=\"dropdown\">" + 
        "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-cog\"></span> Admin<span class=\"caret\"></span></a>" +
	    "<ul class=\"dropdown-menu\">" + 
		    "<li><a href=\"Admin\">Admin Panel</a></li>" + 
		    "<li><a href=\"Tuition\">Tuition</a></li>" + 
		    "<li><a href=\"CourseList\">Course Lookup</a></li>" + 
	    "</ul></li>";
        
        String logOut = "<li><a href=\"login?logout=true\"><span class=\"glyphicon glyphicon-log-in\"></span> Sign Out</a></li>";
		return acc + logOut;
	}
	
	private static String formatAdvisorButtons(long userID, String userName, boolean here, boolean isAdmin) {
		String select = "";
		if (here) {
			select = " class=\"active\"";
		}
		
        String acc = "<li class=\"dropdown\">" + 
        "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"glyphicon glyphicon-cog\"></span> Advisor<span class=\"caret\"></span></a>" +
	    "<ul class=\"dropdown-menu\">" + 
		    "<li><a href=\"Advisor\">View Transcript</a></li>" + 
		    "<li><a href=\"AddDropCourse\">Edit Courses</a></li>" + 
		    "<li><a href=\"CourseList\">Course Lookup</a></li>" + 
	    "</ul></li>";
        
        String logOut = "<li><a href=\"login?logout=true\"><span class=\"glyphicon glyphicon-log-in\"></span> Sign Out</a></li>";
		return acc + logOut;
	}
	
	public static String reformatDate(String dbDate) {
		try {
			java.util.Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dbDate);
			return new SimpleDateFormat("EEEEE, MMMMM dd, yyyy").format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return dbDate;
		}
	}
	
	public static String reformatDate(Date d) {
		return new SimpleDateFormat("EEEEE, MMMMM dd, yyyy").format(d);
	}
	
	public static void processUser(HttpServletRequest request) {
		processUser(request, false);
	}
	
	public static void processUser(HttpServletRequest request, boolean here) {
		// Check for logout request
		String logout  = request.getParameter("logout");
		if (logout != null && logout.equals("true")) {
			request.getSession().setAttribute("userID", null);
			request.getSession().setAttribute("insID", null);
			request.getSession().setAttribute("adminID", null);
			request.getSession().setAttribute("studID", null);
			request.getSession().setAttribute("advisorID", null);
		}
		
		// Display correct buttons
		System.out.println("In processUser");
		String navRight = Util.checkLogin(request, here);
		request.setAttribute("navRight", navRight); 
	}
	
	public static String successAlert(String msg) {
		return "<div class=\"alert alert-success\"><strong>Success! </strong>" + msg + "</div>";
		
	}
	public static String failAlert(String msg) {
		return "<div class=\"alert alert-danger\"><strong>Error! </strong>" + msg + "</div>";
	}
	
}