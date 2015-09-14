<!DOCTYPE html>
<html lang="en">
<head>
	<title>Admin</title>
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
	 	<h2>Orders</h2>
		<table class="table table-hover table-condensed"> <!-- style="width: 500px; position: absolute; left: 80px; top: 150px;" -->
			<thead>
				<tr>
					<th>User</th>
					<th>Product</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Subtotal</th>
				</tr>
			</thead>
		    <tbody>
				${orders}
		    </tbody>
		</table>
	</div>
</body>
</html>