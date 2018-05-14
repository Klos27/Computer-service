<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Current requests<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">
	<div class="col-md-10 offset-md-1">
		<table class="table">
			<thead>
			<tr>
				<th>Request id</th>
				<th>Client id</th>
				<th>Description</th>
				<th>Start date</th>
				<th>End date</th>
			</tr>
			</thead>
			<tbody>

				<c:forEach items="${existingRequests}" var="request" >
					<tr>
						<td>${ request.id } </td>
						<td>${ request.id_client }</td>
						<td>${ request.description }</td>
						<td>${ request.start_date }</td>
						<td>${ request.end_date }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>
<%@ include file="../commons/footer.jspf" %>	