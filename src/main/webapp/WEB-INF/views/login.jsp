<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Login<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="login-form">
			<div class="form-group">
				<label for="email">Enter your e-mail:</label>
				<input type="email" name="email" id="email" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="password">Enter your password:</label>
				<input type="password" name="password" id="password" class="form-control"/>
			</div>
			
		<button type="submit" class="btn btn-danger btn-block">Log in</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	