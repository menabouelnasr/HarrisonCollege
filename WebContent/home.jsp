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

		    <tbody>
				${products}
		    </tbody>
		</table>
	</div>	
</body>
</html>
