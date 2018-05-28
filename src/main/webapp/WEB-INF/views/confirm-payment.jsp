<%@ include file="../commons/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
$(function(){
    $("button").click(function() {
        var button = $(this).val();
        var res = confirm("Confirm payment number " + button + "?");
        if(res){
	        var form = document.createElement("form");
	        form.setAttribute("method", "post");
	        form.setAttribute("action", "/service/confirm-payment");
	        
	        var hiddenField = document.createElement("input");
	        hiddenField.setAttribute("type", "hidden");
	        hiddenField.setAttribute("name", "invoiceId");
	        hiddenField.setAttribute("value", button);
	
	        form.appendChild(hiddenField);
	        document.body.appendChild(form);
	    	form.submit();
        }
    });
});
</script>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Confirm Payment<i class="fas fa-angle-right ml-3"></i></h1>

<c:if test="${not empty userNotification}">
<%-- Notify user --%>
<div class="user-request-notification"> ${userNotification} </div>
</c:if>				
<div class="row">
<div class="col-md-10 offset-md-1">
	<table class="table">
		<thead align="center" valign="middle">
		<tr>
			<th>Request ID</th>
			<th>Invoice ID</th>
			<th>Creation Date</th>
			<th>Amount</th>
			<th>Confirm</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${paymentsList}" var="payment" >		
				<tr>
					<td align="center">${ payment.id_service_request } </td>
					<td align="center">${ payment.id }</td>
					<td align="center">${ payment.creation_date }</td>
					<td align="right">${ payment.amount }</td>
					<td align="center"><button class="btn btn-danger btn-block" name="invoiceId" value="${ payment.id }">Confirm</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>	
	  			
<%@ include file="../commons/footer.jspf" %>	