import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.DBUtil;
import model.Department;
import model.Instructor;
import model.Time;

/**
 * Servlet implementation class CourseList
 */
@WebServlet("/CourseList")
public class CourseList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseList() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//semester, subject, instructor, time, department, major
		String html="";
		List<Course> courses = DBUtil.getCourse("SELECT c FROM Course c");
		List<String> semesters=new ArrayList<String>();
		List<String> subject=new ArrayList<String>();
		for (int i=0; i<courses.size();i++)
		{
			if(!semesters.contains(courses.get(i).getSemester()))
			{
				semesters.add(courses.get(i).getSemester());
			}
			if(!subject.contains(courses.get(i).getSubjectcode()))
			{
				subject.add(courses.get(i).getSubjectcode());
			}
			
		}
		
		List<Department> department = DBUtil.getDepartment("SELECT d FROM Department d");
		List<String> depName=new ArrayList<String>();
		List<String> major=new ArrayList<String>();
		for (int i=0; i<department.size();i++)
		{
			if(!depName.contains(department.get(i).getName()))
			{
				depName.add(department.get(i).getName());
			}
			if(!major.contains(department.get(i).getMajor()))
			{
				major.add(department.get(i).getMajor());
			}
		}
		List<Instructor> instructor = DBUtil.getInstructor("SELECT i FROM Instructor i");
		List<String> prof=new ArrayList<String>();
		for (int i=0; i<instructor.size();i++)
		{
			if(!prof.contains(instructor.get(i).getName()))
			{
				prof.add(instructor.get(i).getName());
			}
		}
		String[] time = {"MWF", "TTH"};
		request.setAttribute("semesters", semesters);
		request.setAttribute("depName", depName);
		request.setAttribute("subject", subject);
		request.setAttribute("major", major);
		request.setAttribute("instructor", prof);
		request.setAttribute("time", time);
		
		getServletContext().getRequestDispatcher("/courselist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String semester = request.getParameter("semester");
		String depName = request.getParameter("depName");
		String instructor = request.getParameter("instructor");
		String major = request.getParameter("major");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
	
		System.out.println(semester + " "+ depName + " "+ instructor + " "+ major + " "+ subject +" "+time);
	}

}
