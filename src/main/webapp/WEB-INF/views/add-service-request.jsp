<%@ include file="../commons/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Add service request<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<form id="add-request-form">
			<div class="form-group">
				<label for="computer_producer">Computer's producer:</label>
				<input type="text" required min="3" max="20" name="computer_producer" id="computer_producer" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="computer_serial">Computer's serial number:</label>
				<input type="text" required min="5" max="24" name="computer_serial" id="computer_serial" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="computer_description">Description:</label>
				<textarea rows="10" required min="4" max="4050" name="computer_description" id="computer_description" class="form-control"></textarea>
			</div>
			
		<button type="submit" class="btn btn-danger btn-block">Add request</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	