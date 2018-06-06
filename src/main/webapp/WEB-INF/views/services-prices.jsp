<%@ include file="../commons/header.jspf" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="${pageContext.request.contextPath}/css/tables.css" rel="stylesheet" type="text/css" > 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
$(function(){
    $("button").click(function() {
        var button = $(this).val();
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/service/operations");
        
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

<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Add new operation<i class="fas fa-angle-right ml-3"></i></h4>

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
			<th>Description</th>
			<th>Price</th>
			<th>Add</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><input type="text" name="operation_name" value="${name_value}" class="form-control"/></td>
				<td align="center"><textarea rows="1" required min="4" max="4050" name="operation_description" id="operation_description" class="form-control">${description_value}</textarea></td>
				<td align="center"><input type="text" name="operation_price" value="${price_value}" class="form-control"/></td>
				<td align="center"><input type="submit" class="btn btn-info" value="Add operation"/></td>
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
			<th>Service ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Update</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><input type="text" name="operation_id" value="${edit_id_value}" class="form-control"/></td>
				<td align="center"><input type="text" name="operation_name" value="${edit_name_value}" class="form-control"/></td>
				<td align="center"><textarea rows="1" required min="4" max="4050" name="operation_description" id="operation_description" class="form-control">${edit_description_value}</textarea></td>
				<td align="center"><input type="text" name="operation_price" value="${edit_price_value}" class="form-control"/></td>
				<td align="center"><input type="submit" class="btn btn-info" value="Update"/></td>
			</tr>
		</tbody>
		</table>
	</div>
</div>		
</form>

<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Operations list<i class="fas fa-angle-right ml-3"></i></h4>
<div class="row">
<div class="col-md-10 offset-md-1">
	<table id="list20" class="table table-striped">
		<thead id="list20" align="center" valign="middle">
		<tr id="list20">
		<form method="get">
			<th id="list20">Operation ID<br /><input type="number" name="f_operation_id" value="${f_id_value}" class="form-control"/></th>
			<th id="list20">Name<br /><input type="text" name="f_operation_name" value="${f_name_value}" class="form-control"/></th>
			<th id="list20">Description<br /><textarea rows="1" max="4050" name="f_operation_description" id="f_operation_description" class="form-control">${f_description_value}</textarea></th>
			<th id="list20">
				Price<br />
				<table style="border:0px">
					<tr>
						<td>
							From:					
						</td>
						<td>
							<input type="text" name="f_operation_price_from" value="${f_price_value_from}" style="width: 120px;" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td>
							To:
						</td>
						<td>
							<input style="width: 120px;" type="text" name="f_operation_price_to" value="${f_price_value_to}" class="form-control"/>
						</td>
					</tr>
				</table>
			</th>
			<th id="list20">Filter<br /><button type="submit" class="btn btn-primary" value="submit">Filter</button><br /> <br /><button type="reset" class="btn btn-danger" value="Reset">Reset</button></th>
			</form>
		</tr>
		</thead>
		<tbody id="list20">
			<c:forEach items="${operationsList}" var="operation" >			
				<tr id="list20">
					<td id="list20" class="filterable-cell" align="center">${ operation.id }</td>
					<td id="list20" class="filterable-cell" align="center">${ operation.name }</td>
					<td id="list20" class="filterable-cell" align="justify">${ operation.description }</td>
					<td id="list20" class="filterable-cell" align="right">${ operation.price }</td>
					<td id="list20" class="filterable-cell" align="center"><button class="btn btn-info" name="fillId" value="${ operation.id }">Edit</button></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>	

<%@ include file="../commons/footer.jspf" %>	