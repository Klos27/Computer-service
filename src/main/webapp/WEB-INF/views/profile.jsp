<%@ include file="../commons/header.jspf" %>

	<h3>Your details:</h3>
	<p>First name: ${ user.first_name }</p>
	<p>Last name: ${ user.last_name }</p>
	<p>Address: ${ user.address }</p>
	<p>Phone number: ${ user.phone }</p>

	<a href="/user/change-details" class="btn btn-success">Change details</a>
	<a href="/user/change-password" class="btn btn-success">Change password</a>

<%@ include file="../commons/footer.jspf" %>	