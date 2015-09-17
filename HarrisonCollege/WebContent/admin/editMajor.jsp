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
        <li><a href="CourseList">Course Lookup</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>
<div class="container">	

	${feedback}

	<h3>Edit Major</h3><br/>
	<form method="post" action="EditMajor">
	<input type="hidden" name="deptID" value="${deptID}"/>
		<table>
			<c:forEach var="dept" items="${list}">
				<label>Major</label>	 <input class="form-control input-sm" type="text" name="major" value="${dept.major}"><br/>
				<label>Department</label><input class="form-control input-sm" type="text" name="name"  value="${dept.name}"><br/>
			</c:forEach>
		</table>
		<button type="submit" class="btn btn-default">Save</button>
	</form>



</div>
</body>
</html>