package source;
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
			String qString = "SELECT u FROM Usr u WHERE u.id = " + userID;
			List<Usr> users = em.createQuery(qString, Usr.class).getResultList();
			for (Usr u : users) {
				return formatSignOutButtons(u.getId(), u.getUsername(), here, isAdmin(userID));
			}
			return loginButtons;
		}
	}
	
	private static boolean isAdmin(long userID) {
		/*EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u FROM ScUser u WHERE u.id = " + userID ;
		List<Usr> users = em.createQuery(qString, Usr.class).getResultList();
		for (Usr u : users) {
			return u.getAdmin();
		}*/
		return false;
	}
	
	private static String formatSignOutButtons(long userID, String userName, boolean here, boolean isAdmin) {
		String select = "";
		if (here) {
			select = " class=\"active\"";
		}
		String admin  = "<li><a href=\"admin\"><span class=\"glyphicon glyphicon-cog\"></span> Admin</a></li>";
        String acc    = "<li" + select + "><a href=\"cart?userID=" + userID + "\"><span class=\"glyphicon glyphicon-user\"></span> " + userName + "'s Cart</a></li>";
        String logOut = "<li><a href=\"login?logout=true\"><span class=\"glyphicon glyphicon-log-in\"></span> Sign Out</a></li>";
        if (!isAdmin) { admin = ""; }
		return admin + acc + logOut;
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
		}
		
		// Display correct buttons
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