<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Admin</title>
<jsp:include page="../includes/navAndHeader.jsp"/>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="list">Products</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>
<div class="container">	

	${feedback}

	<h3>Edit Classroom</h3><br/>
	<form method="post" action="EditCourse">
	<input type="hidden" name="courseID" value="${courseID}"/>
		<table>
			<c:forEach var="course" items="${list}">
				<label>Course #</label>		<input class="form-control input-sm" type="text" name="coursenum"	 value="${course.coursenum}"><br/>
				<label>Credits</label>		<input class="form-control input-sm" type="text" name="credits"  	 value="${course.credits}"><br/>
				<label>Department ID</label><input class="form-control input-sm" type="text" name="deptid"  	 value="${course.deptid}"><br/>
				<label>Description</label>	<input class="form-control input-sm" type="text" name="description"	 value="${course.description}"><br/>
				<label>Enabled?</label>		<input class="form-control input-sm" type="text" name="enabled"  	 value="${course.enabled}"><br/>
				<label>Instructor ID</label><input class="form-control input-sm" type="text" name="instructorid" value="${course.instructorid}"><br/>
				<label>Instructor ID</label><input class="form-control input-sm" type="text" name="name" 		 value="${course.name}"><br/>
				<label>Classroom ID</label>	<input class="form-control input-sm" type="text" name="roomid"  	 value="${course.roomid}"><br/>
				<label>Section</label>		<input class="form-control input-sm" type="text" name="section"  	 value="${course.section}"><br/>
				<label>Semester</label>		<input class="form-control input-sm" type="text" name="semester"  	 value="${course.semester}"><br/>
				<label>Subject Code</label>	<input class="form-control input-sm" type="text" name="subjectcode"  value="${course.subjectcode}"><br/>
				<label>Time ID</label>		<input class="form-control input-sm" type="text" name="timeid"  	 value="${course.timeid}"><br/>
			</c:forEach>
		</table>
		<button type="submit" class="btn btn-default">Save</button>
	</form>



</div>
</body>
</html>