<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Add Course</title>
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
	<div class="jumbotron">

		<div class="col-md-3">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="Advisor">View Transcript</a></li>
				<li><a href="AddDropCourse">Enroll/Drop Class</a></li>
			</ul>
		</div>
		<div class="clearfix visible-lg"></div>
	</div>
</div></body>
</html>