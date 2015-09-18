<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Current Course</title>
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
		<h3>Add / Drop Student's Courses</h3>
		${adddrop}
		${currentg}
	</div>
</body>
</html>