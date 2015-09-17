<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">	
	function showDiv(a) {
   document.getElementById(a).style.display = "block";
	}
		
	</script> 
	
<style type="text/css">
    .bs-example{
    	margin-top: 10%;
    	margin-left: 40%;
    	margin-right: 20%;
    	marfin-bottom: 40%;
    }
      
</style>
<head>

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
${currentCourse}
${tabledisplay}
</div>
	<div class="container">	
${previousCourse}
	${secondtable}
	
	</div>
</body>
</html>