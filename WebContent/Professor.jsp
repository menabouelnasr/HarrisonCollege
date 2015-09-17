<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>InstructHome</title>
<style type="text/css">
    .bs-example{
    	margin-top: 10%;
    	margin-left: 40%;
    	margin-right: 20%;
    	marfin-bottom: 40%;
    }
   
</style>

<jsp:include page="includes/navAndHeader.jsp"/>

    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="list">Course Lookup</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>

	<div class="container">	
		
	
	 	<h2>Courses</h2>
		<table class="table table-hover table-condensed"> <!-- style="width: 500px; position: absolute; left: 80px; top: 150px;" -->
			<thead>
			<tr>
				<th>Semester</th>
				<th>Department</th>
				<th>Major</th>
				<th>Subject</th>
				<th>Instructor</th>
				<th>Section Times</th>
			</tr>
			</thead>
			
				
		    <tbody><tr>
		    <form role="form" method="post" action="Instruct">
			<td><select name="semester">
				<option selected="selected"> Any </option>
				<c:forEach var="item" items="${semesters}">
				<option value='${item}'> ${item}</option>
				</c:forEach>
			</select></td>
			<td><select name="depName">	
				<option selected="selected">Any</option>
				<c:forEach var="item" items="${depName}">
				<option value='${item.id}'>${item.name}</option>
				</c:forEach>
			</select></td>
			<td><select  name="major">
				<option selected="selected">Any</option>
				<c:forEach var="item" items="${depName}">
				<option value='${item.major}'>${item.major}</option>
				</c:forEach>
			</select></td>
			<td><select name="subject">
			<option selected="selected">Any</option>
				<c:forEach var="item" items="${subject}">
				<option value='${item}'>${item}</option>
				</c:forEach>
			</select></td>
			<td><select name="instructor">	
				<option selected="selected">Any</option>
				<c:forEach var="item" items="${instructor}">
				<option value='${item.id}'>${item.name}</option>
				</c:forEach>
			</select></td>
			<td><select name="time">	
				<option selected="selected">Any</option>
				<c:forEach var="item" items="${time}">
				<option value='${item}'>${item}</option>
				</c:forEach>
			</select></td>
			<td><button type="submit" class="btn btn-default">Search</button></td>
			</form>
		    </tr></tbody>
		    
		</table>
	</div>	
	<div class="container">
		${tabledisp}
	</div>
</body>
</html>