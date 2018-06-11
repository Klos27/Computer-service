<%@ include file="../commons/header.jspf" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty userNotification}">
<%-- Notify user about errrors --%>
<div class="user-request-notification"> ${userNotification} </div>
</c:if>


    <%-- Display user's request's details--%>
    <h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Request Details<i class="fas fa-angle-right ml-3"></i></h1>
	    <c:choose>
		    <c:when test="${not empty requestError}">
				<%-- Notify user that this request doesn't belong to him --%>
				<div class="user-request-notification"> ${requestError} </div>
		    </c:when>
		    <c:otherwise>
	       	<%-- Display user's request's details--%>
	  		<%-- Request Description: --%>
	  			  						
			<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table">
					<thead align="center">
						<tr>
							<th>Request id</th>
							<th>Status</th>
							<th>Start Date</th>
							<th>End date</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>						
						<tr>
							<td align="center">${ requestDetails.id }</td>
							<c:choose>
							    <c:when test="${requestDetails.status=='0'}">
		       						<td align="center">New</td>
							    </c:when>
							    <c:when test="${requestDetails.status=='1'}">
		       						<td align="center">Checking</td>
							    </c:when>
							    <c:when test="${requestDetails.status=='2'}">
		       						<td align="center">In porgress</td>
							    </c:when>	
							    <c:when test="${requestDetails.status=='3'}">
		       						<td align="center">Waiting for payment</td>
							    </c:when>
							    <c:when test="${requestDetails.status=='4'}">
		       						<td align="center">Ended</td>
							    </c:when>						    						    					    						        
							    <c:otherwise>
		       						<td align="center">----</td>
							    </c:otherwise>
							</c:choose>
							<td align="center">${ requestDetails.start_date }</td>
							<td align="center">${ requestDetails.end_date }</td>
							<td align="justify">${ requestDetails.description }</td>
							<td align="center"><a href="/service/existing-requests/edit?requestId=${ requestDetails.id }&action=end" class="btn btn-info">End repair</a></td>


							</tr>						
					</tbody>
				</table>
			</div>
			</div>
			
	  		<%-- Service Operations: --%>

	  		<h4 class="text-center mb-4 mt-5"><i class="fas fa-angle-left mr-3"></i>Service operations<i class="fas fa-angle-right ml-3"></i></h4>
	  		<div class="user-request-notification"> ${userNotificationServices} </div>
	  		<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table">
					<thead align="center">
						<tr>
							<th>Service ID</th>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestServicesList}" var="requestService" >
							<tr>
								<td align="center">${ requestService.id }</td>
								<td align="center">${ requestService.name }</td>
								<td align="center">${ requestService.description }</td>
								<td align="center">${ requestService.price }</td>
								<td align="center">
									<form action="/service/existing-requests/edit?requestId=${ requestDetails.id }&deleteService=${ requestService.id }" method="post">
										<input type = "submit" class="btn btn-danger" value = "Delete" />
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center">SUM</td>
							<td></td>
							<td></td>
							<td align="center"><span class="price-higlight">${ requestServicesSum }</span></td>
						</tr>
					</tbody>
				</table>
				
				<form action="/service/existing-requests/edit?requestId=${ requestDetails.id }" method="post">
					<div class="row">
						<div class="col-6">
							<select name="addService" class="form-control">
								<c:forEach items="${allServices}" var="service" >
									<option value = ${service.id}>${service.name}</option>
								</c:forEach>
							</select>						
						</div>
						<div class="col-6">
							<input type = "submit" class="btn btn-info" value = "Add service" />
							<%--<a href="" class="btn btn-info">Add service</a>--%>
						</div>
					</div>
				</form>
				
			</div>
			</div>

	  		<%-- Service Parts: --%>

	  		<h4 class="text-center mb-4 mt-5"><i class="fas fa-angle-left mr-3"></i>Used parts<i class="fas fa-angle-right ml-3"></i></h4>
	  		<div class="user-request-notification"> ${userNotificationParts} </div>
	  		<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table">
					<thead align="center">
						<tr>
							<th>Part ID</th>
							<th>Name</th>
							<th>Price</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestPartsList}" var="requestPart" >
							<tr>
								<td align="center">${ requestPart.id }</td>
								<td align="center">${ requestPart.name }</td>
								<td align="center">${ requestPart.price }</td>
								<td align="center">
									<form action="/service/existing-requests/edit?requestId=${ requestDetails.id }&deletePart=${ requestPart.id }" method="post">
										<input type = "submit" class="btn btn-danger" value = "Delete" />
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center">SUM</td>
							<td></td>
							<td align="center"><span class="price-higlight">${ requestPartsSum }</span></td>
						</tr>
					</tbody>
				</table>

				<form action="/service/existing-requests/edit?requestId=${ requestDetails.id }" method="post">
					<div class="row">
						<div class="col-6">
							<select name="addPart" class="form-control">
								<c:forEach var="part" items="${allParts}">
									<option value = ${part.id}>${part.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-6">
							<input type = "submit" class="btn btn-info" value = "Add part" />
							<%--<a href="#" class="btn btn-info">Add part</a>--%>
						</div>
					</div>
				</form>

			</div>
			</div>

	  		<%-- Chat: --%>		
	  				
	  		<div class="row mt-5">
				<div class="col-md-10 offset-md-1">	
			  		<div class="card">
			  			<div class="card-header text-center font-weight-bold">Chat for request id: ${ requestDetails.id }</div>
			  			<div class="card-body chat-box" id="chat_container"></div>
			  			<div class="card-footer">
			  			
			  			<form id="chat-form">
			  				<div class="row">
			  					<div class="col-md-10 col-8">
			  						<input type="hidden" id="id_user" value="${ user.id }"/>
			  						<input type="hidden" id="id_service_request" value="${ requestDetails.id }"/>
			  						<input type="text" id="content" class="form-control" placeholder="Type a message" />
			  					</div>
			  					<div class="col-md-2 col-4">
			  						<button type="submit" class="btn btn-danger btn-block">Send</button>
			  					</div>
			  				</div>
			  			</form>

			  			</div>
			  		</div>		
	  			</div>
	  		</div>
	  			
	  		<%-- End of Chat: --%>		
	  		
	  		</c:otherwise>
		</c:choose>

<%@ include file="../commons/footer.jspf" %>	