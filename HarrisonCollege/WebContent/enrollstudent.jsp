<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>List</title>
<jsp:include page="includes/navAndHeader.jsp"/>

    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="CourseList">Course Lookup</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>

	<div class="container">	
		<form role="form" method="post" action="StudentSchedule">
			<div class="form-group">
				<input type="text" class="form-control" name="search" placeholder="Search"> <!-- autofocus -->
			</div>
		</form>
		${outcome}
	 	<h2>Enrolled Courses</h2>
		<table class="table table-hover table-condensed"> <!-- style="width: 500px; position: absolute; left: 80px; top: 150px;" -->
			<thead>
			<tr>
				<th>Course Name</th>
				<th>Section</th>
				<th>Description</th>
				<th>Credits</th>
				<th>Instructor</th>
				<th>Course Time</th>
				<th>Course Day</th>
				<th>Subject Code</th>
				<th></th>
			</tr>
			</thead>
				
		    <tbody><tr><td>
		    ${display}
		    </td></tr></tbody>
		    
		</table>
	</div>	
</body>
</html>