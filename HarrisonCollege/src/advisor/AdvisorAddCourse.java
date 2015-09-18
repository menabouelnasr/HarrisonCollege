package advisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class AdvisorAddCourse
 */
@WebServlet("/AdvisorAddCourse")
public class AdvisorAddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);

		int sid = (int) request.getSession().getAttribute("studID");		// student logged in
		int cid = Integer.parseInt(request.getParameter("courseid"));		// course being added
		
		//checkTime(request, sid, cid);
		//checkEnabled(request, sid, cid);
		
		//System.out.println(capacity + "^^^");
		boolean one = checkCapacity(request);
		boolean two = checkTimeAndEnabled(request, sid, cid);
		System.out.println("BOOLS: " + one + two);
		if (one && two) { 	// not over capacity && not duplicate
			Enroll u = new Enroll(sid, cid, "U");
			StudentDB.insert(u);
			request.setAttribute("message", Util.successAlert("Added successfully"));
		}
		
		getServletContext().getRequestDispatcher("/advisorsuccess.jsp").forward(request, response);
	}
	
	private boolean checkDuplicate(HttpServletRequest request, int sid, int cid) {
		for (Object o1 : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + cid)) {
			Course c = (Course)o1;
			for (Object o2 : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + sid)) {
				Enroll e = (Enroll)o2;
				if (e.getCourseid() == c.getId()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkTimeAndEnabled(HttpServletRequest request, int sid, int cid) {
		Course c1 = (Course)DBUtil.get("SELECT c FROM Course c WHERE c.id = " + cid).get(0);
		if (c1.getEnabled() == 0) {
    		request.setAttribute("message", Util.failAlert("Cannot add: Class is not enabled."));
			return false;
		}
		for (Object o2 : DBUtil.get("SELECT e FROM Enroll e WHERE e.studid = " + sid)) {
			Enroll e = (Enroll)o2;
			Course c2 = (Course) DBUtil.get("SELECT c FROM Course c WHERE c.id = " + e.getCourseid()).get(0);
			if (c2.getTimeid() == c1.getTimeid()) {
	    		request.setAttribute("message", Util.failAlert("Cannot add: Student has time overlap with this class."));
				return false;
			}
		}
		return true;
	}
	
	private boolean checkCapacity(HttpServletRequest request) {
	    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
	    	int studID = (int) request.getSession().getAttribute("studID");
	    	String courseID = request.getParameter("courseid");
	    	List<String> enrolledStudents;
	    	
	    	if (!checkDuplicate(request, studID, Integer.parseInt(courseID))) {		// there is a duplicate
	    		request.setAttribute("message", Util.failAlert("Can't Enroll: Student already enrolled in class."));
	    		return false;
	    	} else {
		    	int maxCap = 0, student = 0;
		    	
		    	String qString = "SELECT e FROM Enroll e WHERE e.courseid = " + courseID;
		    	TypedQuery<String> q = em.createQuery(qString, String.class);
		    	enrolledStudents = q.getResultList();
		    	student = enrolledStudents.size();
		    	System.out.println("Num of studs "+student);
		    	System.out.println(courseID);
		    	
		    	List<Course> course = DBUtil.getCourse("SELECT c FROM Course c WHERE c.id = " + courseID);
		    
		    	System.out.println("Course Size: "+course.size());
		    	System.out.println(" room id"+ course.get(0).getRoomid());
		    	System.out.println("SELECT c FROM Classroom c WHERE c.id ='"+course.get(0).getRoomid()+"'");
		    	Classroom room = DBUtil.getOneRoom("SELECT c FROM Classroom c WHERE c.id ='"+course.get(0).getRoomid()+"'");
		    	if (room == null) {
		    		System.out.println("room is null");
		    	}
		    	maxCap = Integer.parseInt(room.getMaxcap());
		    	System.out.println("Max Cap: "+maxCap);
		    	if (maxCap - student < 0) {
		    		System.out.println("return false for max cap");
		    		request.setAttribute("message", Util.failAlert("Cannot add: Class is at max capacity."));
		    		return false;
		    	} else {
		    		return true;
		    	}
		    	
	    	}
	    	
	    }
	 
	/*private String checkDuplicate(HttpServletRequest request, int studID, String courseID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<String> list = new ArrayList<String>();
		String qString = "SELECT e FROM Enroll e WHERE e.courseid = " + courseID + " and e.studid=" + studID;
		TypedQuery<String> q = em.createQuery(qString, String.class);
		list = q.getResultList();
		if (list.isEmpty()) {
			return "true";
		}
		return "false";
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
