<%@ include file="../commons/header.jspf" %>

<div class="row">
	<div class="col-md-6 offset-md-3">
		<div id="contact_details" class="form-group">
			Computer service <br />
			Warszawska 24 <br />
			31-155 Cracow <br />
		</div>

		<div id="google_map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d452.6886577660422!2d19.943884479686673!3d50.071424003073105!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47165b035c03cba3%3A0x7dbd0ef555174f2a!2sPK!5e0!3m2!1spl!2spl!4v1523912538206" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
		</div>

		<form id="contact-form" >
			<p>Contact us!</p>
			<div class="form-group">
				<%--<label>Name</label>--%>
				<input name="name" placeholder="Name">
			</div>
			<div class="form-group">
				<%--<label>Email</label>--%>
				<input name="email" type="email" placeholder="email">
			</div>
			<div class="form-group">
				<%--<label>Message</label>--%>
				<textarea name="message" placeholder="Enter your message message"></textarea>
			</div>
			<input id="submit" name="submit" type="submit" value="Send">

		</form>
	</div>
</div>

<%@ include file="../commons/footer.jspf" %>	