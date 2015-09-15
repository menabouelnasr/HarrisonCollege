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
	</style>
<jsp:include page="includes/navAndHeader.jsp"/>
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
	
	 	<h2>Admin</h2>
	 	
	 	<hr/>
	 	
	 	<h3>Courses</h3>
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
		<a href="NewCourse">Add New Course</a>
		
		<h3>Classrooms</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Building</th><th>Room</th><th>Max Capacity</th><th>Edit</th>
				<th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${rooms}
		    </tbody>
		</table>
		<a href="NewRoom">Add New Classroom</a>
		
		<h3>Departments</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>Department</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${depts}
		    </tbody>
		</table>
		<a href="NewDept">Add New Department</a>
		
		<h3>Majors</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Major</th><th>Department</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${majors}
		    </tbody>
		</table>
		<a href="NewMajor">Add New Major</a>
		
		<hr/>
		<h3>Set User Account Types</h3>
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
</body>
</html>