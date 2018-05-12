<%@ include file="../commons/header.jspf" %>

<h1 class="text-center">Change Password</h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="change-password-form">
			<div class="form-group">
				<label for="password">Current Password:</label>
				<input type="password" required min="4" max="25" name="password" id="password" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="new_password">New password:</label>
				<input type="password" required min="4" max="25" name="new_password" id="new_password" class="form-control"/>
			</div>
			
		<button type="submit" class="btn btn-success btn-block">Change Password!</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	