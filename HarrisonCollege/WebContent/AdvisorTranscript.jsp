<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Advisor Transcript</title>
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
	<h3>Student Transcripts</h3>
	${grades}
	${message}
	</div>
</body>
</html>