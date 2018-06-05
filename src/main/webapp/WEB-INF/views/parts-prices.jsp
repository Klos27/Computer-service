<%@ include file="../commons/header.jspf" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
$(function(){
    $("button").click(function() {
        var button = $(this).val();
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/service/parts");
        
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "fillId");
        hiddenField.setAttribute("value", button);
        form.appendChild(hiddenField);
        
        var hiddenField2 = document.createElement("input");
        hiddenField2.setAttribute("type", "hidden");
        hiddenField2.setAttribute("name", "formAction");
        hiddenField2.setAttribute("value", "fillForm");
        form.appendChild(hiddenField2);
        
        document.body.appendChild(form);
    	form.submit();     
    });
});
</script>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Parts<i class="fas fa-angle-right ml-3"></i></h1>
<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Add new part<i class="fas fa-angle-right ml-3"></i></h4>

<c:if test="${not empty userNotification}">
<%-- Notify user about errors --%>
<div class="user-request-notification"> ${userNotification} </div>
</c:if>

<form method="post">
<input type="hidden" value="add" name="formAction" />
<div class="row">
<div class="col-md-10 offset-md-1">
	<table class="table">
		<thead align="center" valign="middle">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Add</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><input type="text" name="part_name" value="${name_value}"/></td>
				<td align="center"><input type="text" name="part_price" value="${price_value}"/></td>
				<td align="center"><input type="submit" class="btn btn-info" value="Add part"/></td>
			</tr>
		</tbody>
		</table>
	</div>
</div>		
</form>

<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Edit info<i class="fas fa-angle-right ml-3"></i></h4>

<c:if test="${not empty userNotificationEdit}">
<%-- Notify user about errors --%>
<div class="user-request-notification"> ${userNotificationEdit} </div>
</c:if>

<form method="post">
<input type="hidden" value="edit" name="formAction" />
<div class="row">
<div class="col-md-10 offset-md-1">
	<table class="table">
		<thead align="center" valign="middle">
		<tr>
			<th>Part ID</th>
			<th>Name</th>
			<th>Price</th>
			<th>Update</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><input type="text" name="part_id" value="${edit_id_value}"/></td>
				<td align="center"><input type="text" name="part_name" value="${edit_name_value}"/></td>
				<td align="center"><input type="text" name="part_price" value="${edit_price_value}"/></td>
				<td align="center"><input type="submit" class="btn btn-info" value="Update"/></td>
			</tr>
		</tbody>
		</table>
	</div>
</div>		
</form>
 
<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Parts list<i class="fas fa-angle-right ml-3"></i></h4>

<div class="row">
<div style="height:450px;width:750px;overflow:auto;align:center;width:100%;">
<div class="col-md-10 offset-md-1">
	<table class="table">
		<thead align="center" valign="middle">
		<tr>
			<th>Part ID</th>
			<th>Name</th>
			<th>Price</th>
			<th>Edit</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${partsList}" var="part" >			
				<tr>
					<td align="center">${ part.id }</td>
					<td align="center">${ part.name }</td>
					<td align="right">${ part.price }</td>
					<td align="center"><button class="btn btn-info" name="fillId" value="${ part.id }">Edit</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>						
</div>    
   
<%@ include file="../commons/footer.jspf" %>	