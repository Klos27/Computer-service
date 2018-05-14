<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Change details<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="change-details-form">
			<div class="form-group">
				<label for="first_name">First name:</label>
				<input type="text" value="${ user.first_name }" required min="4" max="25" name="first_name" id="first_name" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="last_name">Last name:</label>
				<input type="text" value="${ user.last_name }" required min="4" max="25" name="last_name" id="last_name" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="address">Address:</label>
				<input type="text" value="${ user.address }" min="4" max="64" name="address" id="address" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="phone">Phone number:</label>
				<input type="tel" value="${ user.phone }" min="4" max="13" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}" placeholder="xxx-xxx-xxx" name="phone" id="phone" class="form-control"/>
			</div>
			
		<button type="submit" class="btn btn-danger btn-block">Change details!</button>
		<a href="/user/profile" class="btn btn-secondary btn-block">Go back</a>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	