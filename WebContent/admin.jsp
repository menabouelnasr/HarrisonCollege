<!DOCTYPE html>
<html lang="en">
<head>
	<title>Admin</title>
	<style>
		th.right {
		    text-align: right;
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
				<th>#</th><th>Department</th><th>Major</th><th>Edit</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${depts}
		    </tbody>
		</table>
		<a href="NewDept">Add New Department</a>
		
		<h3>Majors</h3>
		<table class="table table-hover table-condensed">
			<thead><tr>
				<th>#</th><th>Department</th><th>Major</th><th>Edit</th><th class="right">Delete</th>
			</tr></thead>
		    <tbody>
				${majors}
		    </tbody>
		</table>
		<a href="NewMajor">Add New Major</a>
	</div>
</body>
</html>