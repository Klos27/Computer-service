<%@ include file="../commons/header.jspf" %>

<h1 class="text-center">Change details</h1>

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
			
		<button type="submit" class="btn btn-success btn-block">Change details!</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	