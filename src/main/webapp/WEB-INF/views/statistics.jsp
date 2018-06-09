<%@ include file="../commons/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Statistics<i class="fas fa-angle-right ml-3"></i></h1>
<div class="row">
	<div class="col-md-10 offset-md-1">
		Chose month to generate statistics:<br/>
		<form id="show-statistics">
			<table class="table">
			<thead align="center" valign="middle">
			<tr>
				<th>Chose month</th>
				<th>Generate</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center">
						<select id="monthval" class ="form-control">
				    		<option id="monthval" name="1" value="1">January</option>
				    		<option id="monthval" name="2" value="2">February</option>
				    		<option id="monthval" name="3" value="3">March</option>
				    		<option id="monthval" name="4" value="4">April</option>
				    		<option id="monthval" name="5" value="5">May</option>
				    		<option id="monthval" name="6" value="6">June</option>
				    		<option id="monthval" name="7" value="7">July</option>
				    		<option id="monthval" name="8" value="8">August</option>
				    		<option id="monthval" name="9" value="9">September</option>
				    		<option id="monthval" name="10" value="10">October</option>
				    		<option id="monthval" name="11" value="11">November</option>
				    		<option id="monthval" name="12" value="12">December</option>
						</select></td>
					<td align="center"><button type="submit" id="button" class="btn btn-info">Generate</button></td>
				</tr>
			</tbody>
			</table>
		</form>
		Generated statistics: <br/>
		<c:choose>
    	<c:when test="${empty showAvailWithDate}">
    		Nothing to show.
    	</c:when>
    	<c:otherwise>
		<table class="table">
			<thead>
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>ID</th>
				<th>Orders</th>
				<th>Earnings</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${showAvailWithDate}" var="request" >
					<tr>
						<td>${ request.firstname }</td>
						<td>${ request.lastname }</td>
						<td>${ request.id }</td>
						<td>${ request.orders }</td>
						<td>${ request.earnings }</td>
					</tr>
				</c:forEach>
				
				</tbody>
			</table>
		</c:otherwise>
		</c:choose>
	</div>
</div>
<%@ include file="../commons/footer.jspf" %>	