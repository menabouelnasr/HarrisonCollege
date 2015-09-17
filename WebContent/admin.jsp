<!DOCTYPE html>
<html lang="en">
<head>
	<title>Admin</title>
	<style>
		th.right {
		    text-align: right;
		}
		td.this {
			padding: 0px 10px 0px 10px;
		}
		.tt {
			position: relative;
			left: 15px;
			top: 10px;
		}
		.sections {
			margin-right: 50px;
		}
		
		body {
		 	background-image: url('http://diplomaclassics.com/images/Entities/insignia/v2/HacoSealGold_220.png');
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-position: center;
		}
		
/* navbar */
.navbar-custom {
    background-color: #0A4600;
    border-color: #E7E7E7;
}
/* title */
.navbar-custom .navbar-brand {
    color: #97D38D;
}
.navbar-custom .navbar-brand:hover,
.navbar-custom .navbar-brand:focus {
    color: #ffffff;
}
/* link */
.navbar-custom .navbar-nav > li > a {
    color: #97D38D;		// Actual links
}
.navbar-custom .navbar-nav > li > a:hover,
.navbar-custom .navbar-nav > li > a:focus {
	background-color: #64B058;
    color: #ffffff;		// Text when hovered
    
}
.navbar-custom .navbar-nav > .active > a, 
.navbar-custom .navbar-nav > .active > a:hover, 
.navbar-custom .navbar-nav > .active > a:focus {
    color: #97D38D;
    background-color: #1E6912;
}
.navbar-custom .navbar-nav > .open > a, 
.navbar-custom .navbar-nav > .open > a:hover, 
.navbar-custom .navbar-nav > .open > a:focus {
    color: #555;
    background-color: rgb(255,0,0);
}
/* caret */
.navbar-custom .navbar-nav > .dropdown > a .caret {
    border-top-color: #777;
    border-bottom-color: #777;
}
.navbar-custom .navbar-nav > .dropdown > a:hover .caret,
.navbar-custom .navbar-nav > .dropdown > a:focus .caret {
    border-top-color: #333;
    border-bottom-color: #333;
}
.navbar-custom .navbar-nav > .open > a .caret, 
.navbar-custom .navbar-nav > .open > a:hover .caret, 
.navbar-custom .navbar-nav > .open > a:focus .caret {
    border-top-color: #555;
    border-bottom-color: #555;
}
/* mobile version */
.navbar-custom .navbar-toggle {
    border-color: #DDD;
}
.navbar-custom .navbar-toggle:hover,
.navbar-custom .navbar-toggle:focus {
    background-color: #DDD;
}
.navbar-custom .navbar-toggle .icon-bar {
    background-color: #CCC;
}
		
		
	</style>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<nav class="navbar navbar-custom">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="Admin">Harrison College</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="Admin">Courses</a></li>
        <li class="active" ><a href="Admin">Admin</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>

	<div class="container">	
		<a href="top"></a>
	
		${feedback}
	
	 	<h2>Admin</h2>
	 	<a href="#courses" class="sections">Courses</a> 
	 	<a href="#classrooms" class="sections">Classrooms</a> 
	 	<a href="#departments" class="sections">Departments</a> 
	 	<a href="#majors" class="sections">Majors</a> 
	 	<a href="#accTypes" class="sections">Change User's Account Types</a> 
	 	<a href="Tuition" class="sections">Tuition Stats</a> 
	 	<hr/>
	 	
	 	<a name="courses"></a><h3>Courses</h3>
	 	<a href="#top">^ Back to top ^</a> 
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Enabled?</th><th>Dept</th><th>Subj</th><th>Course#</th>
				<th>Section</th><th>Time</th><th>Instr</th><th>Room</th><th>Name</th>
				<th>Desc</th><th>Credits</th><th>Semester</th><th>Edit</th>
				<th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${courses}
		    </tbody>
		</table>
		<a class="btn btn-default" href="NewCourse">Add New Course</a>
		
		<br/><hr/><br/>
		
		<a name="classrooms"></a><h3>Classrooms</h3> 
		<a href="#top">^ Back to top ^</a> 
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Building</th><th>Room</th><th>Max Capacity</th><th>Edit</th>
				<th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${rooms}
		    </tbody>
		</table>
		<a class="btn btn-default" href="NewRoom">Add New Classroom</a>
		
		<br/><hr/><br/>
		
		<a name="departments"></a><h3>Departments</h3>
		<a href="#top">^ Back to top ^</a> 
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>Department</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${depts}
		    </tbody>
		</table>
		<a class="btn btn-default" href="NewDept">Add New Department</a>
		
		<br/><hr/><br/>
		
		<a name="majors"></a><h3>Majors</h3>
		<a href="#top">^ Back to top ^</a> 
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Major</th><th>Department</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${majors}
		    </tbody>
		</table>
		<a class="btn btn-default" href="NewMajor">Add New Major</a>
		
		<br/><hr/><br/>
		
		<a name="accTypes"></a><h3>Set User Account Types</h3>
		<a href="#top">^ Back to top ^</a> 
		<form method="post" action="Admin">
			<table width="100%">
			<thead><th><div class="tt">ID</div></th><th><div class="tt">Type</div></th><th></th></thead>
			<tr><td width="100%" class="this">
				<label> </label>	<input class="form-control input-sm" type="text" name="userID"   placeholder="User ID"><br/>
				</td><td class="this">
				
				<select name="userType" >
				  <option value="Student">Student</option>
				  <option value="Instructor">Instructor</option>
				  <option value="Advisor">Advisor</option>
				  <option value="Admin">Admin</option>
				</select>
				</td><td class="this">
				<button type="submit" class="btn btn-default">Save</button>
			</td></tr></table>
		</form>
		
	</div>
	<br/><br/><br/><br/>
</body>
</html>