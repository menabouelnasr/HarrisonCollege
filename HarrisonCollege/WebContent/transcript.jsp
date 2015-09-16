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
<body>
	<div class="container">	
	${feedback}
	 	<h2>Transcript</h2>
		<table class="table table-hover table-condensed"> <!-- style="width: 500px; position: absolute; left: 80px; top: 150px;" -->
			<thead>
			<tr>
				<th>Semester</th>
				<th>Course Name</th>
				<th>Section</th>
				<th>Credits</th>
				<th>Instructor</th>
				<th>Subject Code</th>
				<th>Grade</th>
			</tr>
			</thead>
			
		    <tbody><tr><td>
		    ${display}
		    </td></tr></tbody>
		    
		</table>
		  
		<form role="form" method="post" action="Transcript" >

		<button type="submit" class="btn btn-default">Purchase Transcript</button>

		</form>
</body>
</html>