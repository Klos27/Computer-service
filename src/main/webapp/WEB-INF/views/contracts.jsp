<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Contract list<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row">

	<div class="col-md-10 offset-md-1">
		<table class="table">
			<thead>
			<tr>
				<th>User ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Role</th>
				<th>Start date</th>
				<th>End date</th>
				<th>Salary</th>
				<th>Action</th>
			</tr>
			</thead>
			<tbody>

			<c:forEach items="${userContractList}" var="userContract" >
				<tr>
					<td align="center">${ userContract.userId } </td>
					<td align="center">${ userContract.firstName }</td>
					<td align="center">${ userContract.lastName }</td>
					<td align="center">
						<c:choose>
							<c:when test="${ userContract.role  == 3}">
								Worker
							</c:when>
							<c:when test="${ userContract.role  == 2}">
								Foreman
							</c:when>
							<c:when test="${ userContract.role  == 1}">
								Admin
							</c:when>
						</c:choose></td>
					<td align="center">${ userContract.dateStart }</td>
					<td align="center">${ userContract.dateEnd }</td>
					<td align="center">${ userContract.salary }</td>
					<td align="center">
						<form action="#" method="post">
							<input type = "submit" class="btn btn-danger" value = "End contract" />
						</form>
					</td>

				</tr>
			</c:forEach>

			</tbody>
		</table>
	</div>

</div>

<%@ include file="../commons/footer.jspf" %>
