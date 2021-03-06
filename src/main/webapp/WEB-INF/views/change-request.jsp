<%@ include file="../commons/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Change existing requests<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-10 offset-md-1">
	List of taken requests by specific workers.
		<table class="table">
			<thead>
			<tr>
				<th>Request id</th>
				<th>Client id</th>
				<th>Description</th>
				<th>Start date</th>
				<th>End date</th>
				<th>Worker id</th>
				<th>Firstname</th>
				<th>Lastname</th>
			</tr>
			</thead>
			<tbody>

				<c:forEach items="${existingReqWithWorkers}" var="request" >
					<tr>
						<td>${ request.id } </td>
						<td>${ request.id_client }</td>
						<td>${ request.description }</td>
						<td>${ request.start_date }</td>
						<td>${ request.end_date }</td>
						<td>${ request.id_employee }</td>
						<td>${ request.firstname }</td>
						<td>${ request.lastname }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		If you want to assign a different request to a different worker, please direct on this list.
		<table class="table">
			<thead>
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>ID</th>
				<th>Number of tasks</th>
			</tr>
			</thead>
			<tbody>

				<c:forEach items="${showAvail}" var="request" >
					<tr>
						<td>${ request.firstname } </td>
						<td>${ request.lastname } </td>
						<td>${ request.id }</td>
						<c:choose>
							<c:when test = "${request.id_service_request > 1}">
   								<td><font style="color:#ff0000; font-weight: bold">${ request.id_service_request }</font></td>
   							</c:when>
   							<c:when test = "${request.id_service_request == 1}">
   								<td><font style="color:#ffd800; font-weight: bold">${ request.id_service_request }</font></td>
   							</c:when>
   							<c:otherwise>
   								<td><font style="color:#59ff00; font-weight: bold">${ request.id_service_request }</font></td>
   							</c:otherwise>
   						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		Re-assign request:<br/>

		<form id="change-request">
			<table class="table">
			<thead align="center" valign="middle">
			<tr>
				<th>Chose worker</th>
				<th>Type ID of request</th>
				<th>Re-assign</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center">
						<select id="empid" class ="form-control">
							<c:forEach items="${showAvail}" var="request">
				    			<option id="empid" name="${request.id}" value="${request.id}">${request.firstname} ${request.lastname}</option>
							</c:forEach>
						</select></td>
					<td align="center"><input type="number" id="reqid" oninput="success()" name="reqid" placeholder="type ID of request" class="form-control input-sm"/></td>
					<td align="center"><button type="submit" id="button" disabled class="btn btn-info">Change</button></td>
				</tr>
			</tbody>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
function success() {
	 if(document.getElementById("reqid").value==="") { 
           document.getElementById('button').disabled = true; 
       } else { 
           document.getElementById('button').disabled = false;
       }
   }
</script>
<%@ include file="../commons/footer.jspf" %>	