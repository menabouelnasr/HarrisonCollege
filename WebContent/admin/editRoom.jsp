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
	<form method="post" action="EditRoom">
	<input type="hidden" name="roomID" value="${roomID}"/>
		<table>
			<c:forEach var="room" items="${list}">
				<label>Building Name</label><input class="form-control input-sm" type="text" name="bldgname" value="${room.bldgname}"><br/>
				<label>Max Capacity</label>	<input class="form-control input-sm" type="text" name="maxcap"   value="${room.maxcap}"><br/>
				<label>Room Number</label>	<input class="form-control input-sm" type="text" name="roomnum"  value="${room.roomnum}"><br/>
			</c:forEach>
		</table>
		<button type="submit" class="btn btn-default">Save</button>
	</form>



</div>
</body>
</html>