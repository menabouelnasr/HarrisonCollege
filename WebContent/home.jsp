<!DOCTYPE html>
<html lang="en">
<head>
	<title>List</title>
<jsp:include page="includes/navAndHeader.jsp"/>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="list">Items</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
    	${navRight}
      </ul>
    </div>
  </div>
</nav>

	<div class="container">	
		<form role="form" method="post" action="list">
			<div class="form-group">
				<input type="text" class="form-control" name="search" placeholder="Search"> <!-- autofocus -->
			</div>
		</form>
	
	 	<h2>Products</h2>
		<!-- <table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>Picture</th>
					<th>Name</th>
					<th>Price</th>
					<th>Availability</th>
				</tr>
			</thead>
			 -->
		    <tbody>
				${products}
		    </tbody>
		</table>
	</div>	
</body>
</html>
