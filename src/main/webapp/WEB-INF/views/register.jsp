<%@ include file="../commons/header.jspf" %>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="register-form">
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" required min="4" max="25" name="username" id="username" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" required min="4" max="25" name="password" id="password" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="password2">Re-enter password:</label>
				<input type="password" required min="4" max="25" name="password2" id="password2" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="email">E-mail:</label>
				<input type="email" required name="email" id="email" class="form-control"/>
			</div>
			
		<button type="submit" class="btn btn-success btn-block">Register</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	