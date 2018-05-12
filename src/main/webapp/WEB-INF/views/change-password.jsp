<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Change password<i class="fas fa-angle-right ml-3"></i></h1>

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
			
			<button type="submit" class="btn btn-danger btn-block">Change password!</button>
			<a href="/user/profile" class="btn btn-secondary btn-block">Go back</a>		
			
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	