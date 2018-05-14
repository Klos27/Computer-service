<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Your details<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-2">
		<p>First name: ${ user.first_name }</p>
		<p>Last name: ${ user.last_name }</p>
		<p>Address: ${ user.address }</p>
		<p>Phone number: ${ user.phone }</p>	
	</div>
	<div class="col-md-3 text-center">
		<div class="mb-2"><a href="/user/change-details" class="btn btn-danger">Change details</a></div>
		<div class="mb-2"><a href="/user/change-password" class="btn btn-danger">Change password</a></div>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	