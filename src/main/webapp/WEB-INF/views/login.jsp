<%@ include file="../commons/header.jspf" %>

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
			
		<button type="submit" class="btn btn-success btn-block">Log in</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	