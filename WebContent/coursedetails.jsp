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
</nav>
<div>

${tabledisplay}
</div>
	<div>

	${secondtable}
	</div>
</body>
</html>