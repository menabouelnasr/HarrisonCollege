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

	<h3>New Major</h3><br/>
	<form method="post" action="NewMajor">
	<input type="hidden" name="courseID" value="${courseID}"/>
		<label>Course #</label>	<input class="form-control input-sm" type="text" name="coursenum" value="${dept.major}"><br/>
		<label>Credits</label>	<input class="form-control input-sm" type="text" name="credits"   value="${dept.name}"><br/>
		<button type="submit" class="btn btn-default">Save</button>
	</form>



</div>
</body>
</html>