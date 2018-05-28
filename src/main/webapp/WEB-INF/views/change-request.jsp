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
					</tr>
				</c:forEach>
			</tbody>
		</table>
		If you want to assign a different request to a different worker, please direct on this list.
		<table class="table">
			<thead>
			<tr>
				<th>Name of worker</th>
				<th>ID</th>
				<th>Number of tasks</th>
			</tr>
			</thead>
			<tbody>

				<c:forEach items="${showAvail}" var="request" >
					<tr>
						<td>${ request.firstname } </td>
						<td>${ request.id }</td>
   						<td>${ request.id_service_request }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!--  Re-assign request:<br/>
		<select>
			<c:forEach items="${showAvail}" var="request">
    			<option value="${request.id}">${request.firstname}</option>
			</c:forEach>
		</select>
		<form action="ChangeRequest" method="post">
			<input type="number" id="reqid" name="reqid" placeholder="type ID of request" />
			<input type="submit" value="ok" />
		</form>-->
	</div>

</div>
<%@ include file="../commons/footer.jspf" %>	