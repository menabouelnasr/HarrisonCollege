<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<title>Admin</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="Homepage">Harrison College</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav navbar-right">
	    	${navRight}
	      </ul>
	    </div>
	  </div>
	</nav>

	<div class="container">	
	
		${feedback}
	
	 	<h2>Tuition Stats</h2>
	 	
	 	<!--form method="post" action="Admin">
			<table width="150px" border="0">
			<thead><th><div class="tt2">Semester</div></th><th></th></thead>
			<tr><td class="this">
				<select name="userType" >
				  <option value="Fall 2015">Fall 2015</option>
				  <option value="Spring 2015">Spring 2015</option>
				</select>
				</td><td class="this">
				<button type="submit" class="btn btn-default btn-sm">Go</button>
			</td></tr></table>
		</form-->
		
		<form method="post" action="Tuition">
			<table width="100%">
			<thead>
				<th><div class="tt">Tuition Per Credit</div></th><th><div class="tt">Semester</div></th><th></th>
			</thead>
			<tr>
				<td width="100%" class="this">
					<label> </label>	
					<input class="form-control input-sm" type="text" name="perCredit" value="${perCredit}"><br/>
				</td>
				<!-- td class="this">
					<select name="semester">
					  <option value="Fall 2015">Fall 2015</option>
					  <option value="Spring 2015">Spring 2015</option>
					</select>
				</td -->
				
				<td class="this"><select name="semester">
					<option selected="selected"> Any </option>
					<c:forEach var="item" items="${semesters}">
					<option value='${item}'> ${item}</option>
					</c:forEach>
				</select></td>
				
				
				<td class="this">
					<button type="submit" class="btn btn-default">Go</button>
				</td>
			</tr>
			</table>
		</form>
	 	
	 	<h3>By Department</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>Department</th><th>Revenue</th>
			</tr></thead>
		    <tbody>
				${byDept}
		    </tbody>
		</table>
		
		<h3>By Instructor</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>Instructor</th><th>Revenue</th>
			</tr></thead>
		    <tbody>
				${byInst}
		    </tbody>
		</table>
		
		<h3>By Course & Class</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>Class</th><th>Revenue</th>
			</tr></thead>
		    <tbody>
				${byCour}
		    </tbody>
		</table>
			
</body>
</html>
















