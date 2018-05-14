<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Register<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="register-form">
			<div class="form-group">
				<label for="first_name">First name:</label>
				<input type="text" required min="4" max="25" name="first_name" id="first_name" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="last_name">Last name:</label>
				<input type="text" required min="4" max="25" name="last_name" id="last_name" class="form-control"/>
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
			
		<button type="submit" class="btn btn-danger btn-block">Register</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	