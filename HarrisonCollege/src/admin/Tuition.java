package admin;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/Tuition")
public class Tuition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);	
		
		// get semesters
		java.util.List<Object>semesters = DBUtil.get("SELECT c.semester FROM Course c");
		
		// set semesters
		request.setAttribute("semesters", semesters);
		
		request.setAttribute("perCredit", "500");
		
		getServletContext().getRequestDispatcher("/admin/tuition.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.processUser(request);
		String semester = "Spring 2015";//request.getParameter("semester");
		double PER_CREDIT = Double.parseDouble(request.getParameter("perCredit"));
		request.setAttribute("perCredit", PER_CREDIT);
		DecimalFormat df = new DecimalFormat("0.00"); 
		
		
		String byDept = "";
		for (Object o3 : DBUtil.get("SELECT DISTINCT d.name FROM Department d")) {
			double revenueByDept = 0.0;
			String s = (String)o3;
			for (Object o4 : DBUtil.get("SELECT d FROM Department d WHERE d.name = '" + s + "'")) {
				Department d = (Department)o4;
				for (Object o1 : DBUtil.get("SELECT e FROM Enroll e")) {
					Enroll e = (Enroll)o1;
					for (Object o2 : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + e.getCourseid())) {
						Course c = (Course)o2;
						if (c.getSemester().equals(semester) && c.getDeptid() == d.getId()) {
							revenueByDept += PER_CREDIT * Integer.parseInt(c.getCredits());
						}
					}
				}
			}
			byDept += "<tr><td>" + s + "</td>";
			byDept += "<td>$" + df.format(revenueByDept) + "</td><tr/>";
		}
		request.setAttribute("byDept", byDept);
		
		
		String byInst = "";
		for (Object o3 : DBUtil.get("SELECT i FROM Instructor i")) {
			double revenueByInst = 0.0;
			model.Instructor i = (model.Instructor)o3;
			for (Object o1 : DBUtil.get("SELECT e FROM Enroll e")) {
				Enroll e = (Enroll)o1;
				for (Object o2 : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + e.getCourseid())) {
					Course c = (Course)o2;
					if (c.getSemester().equals(semester) && c.getInstructorid() == i.getId()) {
						revenueByInst += PER_CREDIT * Integer.parseInt(c.getCredits());
					}
				}
			}
			byInst += "<tr><td>" + i.getName() + "</td>";
			byInst += "<td>$" + df.format(revenueByInst) + "</td><tr/>";
		}
		request.setAttribute("byInst", byInst);
		

		// Absolutely Hideous Code
		String byCour = "";
		
		// Each Course
		for (Object o1 : DBUtil.get("SELECT DISTINCT c.subjectcode FROM Course c")) {
			String sc = (String)o1;
			for (Object o2 : DBUtil.get("SELECT DISTINCT c.coursenum FROM Course c WHERE c.subjectcode = '" + sc + "'")) {
				String cn = (String)o2;
				String cc = sc + cn;
				double revenueByCour = 0.0;
				
				// Each Class of this Course
				for (Object o3 : DBUtil.get("SELECT c FROM Course c WHERE c.subjectcode = '" + sc + "' AND c.coursenum = '" + cn + "'")) {
					Course c = (Course)o3;
					String ccs = cc + ":" + c.getSection();
					double revenueByClas = 0.0;
					if (c.getSemester().equals(semester)) {
						byDept += "<tr><td>" + c.getName() + "</td></tr>";
						
						// Each Student in the class
						for (Object o4 : DBUtil.get("SELECT e FROM Enroll e WHERE e.courseid = " + c.getId())) {										
							revenueByCour += PER_CREDIT * Integer.parseInt(c.getCredits());
							revenueByClas += PER_CREDIT * Integer.parseInt(c.getCredits());
						}
					}
					byCour += "<td>" + ccs + "</td>";
					byCour += "<td>&#9;$" + df.format(revenueByClas) + "</td><tr/>";
				}
				byCour += "<tr><th class=\"tf\">" + cc + "</th>";
				byCour += "<td class=\"tf\">$" + df.format(revenueByCour) + "</td><tr/>";
				byCour += "<tr><td></td><td></td></tr>";
			}
			
		}
		request.setAttribute("byCour", byCour);
		
		
		getServletContext().getRequestDispatcher("/admin/tuition.jsp").forward(request, response);
	}
}
































/*
String byCour = "";		
for (Object o1 : DBUtil.get("SELECT DISTINCT c.subjectcode FROM Course c")) {
	String s = (String)o1;
	double revenueByCour = 0.0;
	for (Object o2 : DBUtil.get("SELECT c FROM Course c WHERE c.subjectcode = '" + s + "'")) {
		double revenueByClas = 0.0;
		Course c = (Course)o2;
		if (c.getSemester().equals(semester)) {
			byDept += "<tr><td>" + c.getName() + "</td></tr>";
			for (Object o3 : DBUtil.get("SELECT e FROM Enroll e")) {
				Enroll e = (Enroll)o3;
				revenueByCour += PER_CREDIT * Integer.parseInt(c.getCredits());
				revenueByClas += PER_CREDIT * Integer.parseInt(c.getCredits());
			}
		}
		byCour += "<td>" +  + "</td>";
		byCour += "<td>&#9;" + revenueByClas + "</td><tr/>";
	}
	byCour += "<tr><td>" + i.getName() + "</td>";
	byCour += "<td>" + revenueByCour + "</td><tr/>";
}
request.setAttribute("byCour", byCour);
*/
/*String deptName = request.getParameter("deptName");
String instName = request.getParameter("instName");
String courName = request.getParameter("courName");
String courseID = request.getParameter("courseID");*/
/*
double revenueByDept = 0.0;
double revenueByInst = 0.0;
double revenueByCour = 0.0;
double revenueByClas = 0.0;
for (Object o1 : DBUtil.get("SELECT e FROM Enroll e")) {
	Enroll e = (Enroll)o1;
	for (Object o2 : DBUtil.get("SELECT c FROM Course c WHERE c.id = " + e.getCourseid())) {
		Course c = (Course)o2;
		if (c.getSemester().equals(semester)) {
		
			// Department
			for (Object o3 : DBUtil.get("SELECT d FROM Department d WHERE d.id = " + c.getDeptid())) {
				Department d = (Department)o3;
				if (d.getName().equals(deptName)) {
					revenueByDept += PER_CREDIT * Integer.parseInt(c.getCredits());
				}						
			}
			
			// Instructor
			for (Object o3 : DBUtil.get("SELECT i FROM Instructor i WHERE i.id = " + c.getInstructorid())) {
				model.Instructor i = (model.Instructor)o3;
				if (i.getName().equals(instName)) {
					revenueByInst += PER_CREDIT * Integer.parseInt(c.getCredits());
				}
			}
			
			// Course (ex: ENEG101)
			String courseName = c.getSubjectcode() + c.getCoursenum();
			if (courseName.equals(courName)) {
				revenueByCour += PER_CREDIT * Integer.parseInt(c.getCredits());
			}
			
			// Class (ex: ENEG101:0101 fall 2013)
			if (c.getId() == Long.parseLong(courseID)) {
				revenueByClas += PER_CREDIT * Integer.parseInt(c.getCredits());
			}
			
		}
						
	}

}


request.setAttribute("revenueByDept", revenueByDept);
request.setAttribute("revenueByInst", revenueByInst);
request.setAttribute("revenueByCour", revenueByCour);
request.setAttribute("revenueByClas", revenueByClas);
*/