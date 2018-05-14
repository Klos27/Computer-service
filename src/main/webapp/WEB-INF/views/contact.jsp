<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Contact<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<div id="contact_details" class="form-group">
			Computer service <br />
			Warszawska 24 <br />
			31-155 Cracow <br />
		</div>

		<div id="google_map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d452.6886577660422!2d19.943884479686673!3d50.071424003073105!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47165b035c03cba3%3A0x7dbd0ef555174f2a!2sPK!5e0!3m2!1spl!2spl!4v1523912538206" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
		</div>

		<form id="contact-form" >
			<p>Contact us!</p>
			
			<div class="form-group">
				<label for="name">Name:</label>
				<input type="text" required min="4" max="25" name="name" id="name" class="form-control"/>
			</div>

			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" required name="email" id="email" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label for="message">Message:</label>
				<textarea name="message" id="message" class="form-control"></textarea>
			</div>
			
			<button type="submit" class="btn btn-danger btn-block">Send a message!</button>

		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	