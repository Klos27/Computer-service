<%@ include file="../commons/header.jspf" %>

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
			
		<button type="submit" class="btn btn-success btn-block">Add request</button>
		
		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	