<%@ include file="../commons/header.jspf" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty userNotification}">
	<%-- Notify user about errrors --%>
	<div class="user-request-notification"> ${userNotification} </div>
</c:if>


<jsp:useBean id="now" class="java.util.Date" />

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
						<%--<form action="/service/contracts?endContract=${ userContract.contractId }" method="post">--%>
						<form action="/service/contracts" method="post">
							<input type = "hidden" name="endContract" class="btn btn-danger" value = "${ userContract.contractId }" />
							<input type = "hidden" name="endDate" class="btn btn-danger" value = "${ userContract.dateEnd }" />
							<c:if test="${ userContract.dateEnd  > now}">
								<input type = "submit" class="btn btn-danger" value = "End contract" />
							</c:if>
							<c:if test="${ userContract.dateEnd <= now}">
								<input type = "submit" class="btn btn-danger disabled" value = "End contract" />
							</c:if>

						</form>
					</td>

				</tr>
			</c:forEach>

			</tbody>
		</table>
	</div>

</div>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Add contract<i class="fas fa-angle-right ml-3"></i></h1>

<div class="col-md-10 offset-md-1">



	<form action="/service/contracts" method="post">
		<div class="row">
			<div class="col-6">
				<select name="userId" class="form-control">
					<c:forEach items="${usersWithoutContract}" var="user">
						<option value="${user.id}"> ${user.first_name} ${user.last_name}</option>
					</c:forEach>
				</select>
				<div class="form-group">
					<label for="start_date">Start date:</label>
					<input type="date" name="start_date" id="start_date" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="end_date">End date:</label>
					<input type="date" name="end_date" id="end_date" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="salary">Salary:</label>
					<input type="number" name="salary" id="salary" class="form-control"/>

					<button type="submit" class="btn btn-info">Add contract</button>
				</div>
			</div>


		</div>
	</form>

</div>
<%@ include file="../commons/footer.jspf" %>
