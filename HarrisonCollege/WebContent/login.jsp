<!-- deposit.html -->
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Log In</title>
<jsp:include page="includes/navAndHeader.jsp"/>
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

	<table class="table table-condensed">
	<tr>
	<td>
		<h3>Create Account</h3>
		<form role="form" method="post" action="login">
			<input type="hidden" name="type" value="1">
			<div class="form-group">
				<input type="text" class="form-control" name="username" placeholder="Please create a username">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="email" placeholder="Please enter your email address">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="password" placeholder="Please create a password">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="entry" placeholder="Please enter your entry year">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="grad" placeholder="Please enter your graduation year">
			</div>
			<div class="form-group">
			<select  name="major">
				<option selected="selected">Major</option>
				<c:forEach var="item" items="${major}">
				<option value='${item}'>${item}</option>
				</c:forEach>
			</select>
			</div>
			<button type="submit" class="btn btn-default" id="submit">Sign Up</button>
		</form>
	</td>
	<td>
		<h3>Log In</h3>
		<form role="form" method="post" action="login">
			<input type="hidden" name="type" value="2">
			<div class="form-group">
				<input type="text" class="form-control" name="username" placeholder="Please enter your username">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="password" placeholder="Please enter your password">
			</div>
			<button type="submit" class="btn btn-default" id="submit">Log In</button>
		</form>
	</td>
	</tr>
	</table>


	</div>

</body>
</html>