<%@ include file="../commons/header.jspf" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty userNotification}">
<%-- Notify user about errrors --%>
	${userNotification}
</c:if>

<c:choose>
    <c:when test="${not empty userExistingRequests}">
    <%-- Display all user's requests--%>
    <h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>View requests<i class="fas fa-angle-right ml-3"></i></h1>
		<div class="row">
		<div class="col-md-10 offset-md-1">
			<table class="table">
				<thead align="center" valign="middle">
				<tr>
					<th>Request id</th>
					<th>Status</th>
					<th>Start Date</th>
					<th>End date</th>
					<th>Description</th>
					<th>Details</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${userExistingRequests}" var="request" >			
						<tr>
							<td align="center"><a href="?id=${ request.id }"> ${ request.id } </a></td>
							<c:choose>
							    <c:when test="${request.status=='0'}">
		       						<td align="center">New</td>
							    </c:when>
							    <c:when test="${request.status=='1'}">
		       						<td align="center">Checking</td>
							    </c:when>
							    <c:when test="${request.status=='2'}">
		       						<td align="center">In porgress</td>
							    </c:when>	
							    <c:when test="${request.status=='3'}">
		       						<td align="center">Waiting for payment</td>
							    </c:when>
							    <c:when test="${request.status=='4'}">
		       						<td align="center">Ended</td>
							    </c:when>						    						    					    						        
							    <c:otherwise>
		       						<td align="center">----</td>
							    </c:otherwise>
							</c:choose>
							<td align="center">${ request.start_date }</td>
							<td align="center">${ request.end_date }</td>
							<td align="justify">${ request.description }</td>
							<td align="center"><a href="?id=${request.id}" class="btn btn-info">Details</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>						
    </c:when>			    					    						        
    <c:otherwise>
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
							<th>Get Invoice</th>
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
							<td align="center"><a href="?" class="btn btn-info">Download</a></td>
						</tr>						
					</tbody>
				</table>
			</div>
			</div>			
			
	  		<%-- Service Operations: --%>	
	  		
	  		<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Service operations<i class="fas fa-angle-right ml-3"></i></h4>
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestServicesList}" var="requestService" >			
							<tr>
								<td align="center">${ requestService.id }</td>
								<td align="center">${ requestService.name }</td>
								<td align="center">${ requestService.description }</td>
								<td align="center">${ requestService.price }</td>
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
			</div>
			</div>			
	  		
	  		<%-- Service Parts: --%>
	  		
	  		<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Used parts<i class="fas fa-angle-right ml-3"></i></h4>
	  		<div class="user-request-notification"> ${userNotificationParts} </div>
	  		<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table">
					<thead align="center">
						<tr>
							<th>Part ID</th>
							<th>Name</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestPartsList}" var="requestPart" >			
							<tr>
								<td align="center">${ requestPart.id }</td>
								<td align="center">${ requestPart.name }</td>
								<td align="center">${ requestPart.price }</td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center">SUM</td>
							<td></td>
							<td align="center"><span class="price-higlight">${ requestPartsSum }</span></td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>								  							  						
	  						 						
	  		<%-- Payments: --%>	
	  				
			<h4 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Payment info<i class="fas fa-angle-right ml-3"></i></h4>
	  		<div class="user-request-notification"> ${ userNotificationPayment } </div>		
  			<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table">
					<thead align="center">
						<tr>
							<th>Invoice ID</th>
							<th>Amount</th>
							<th>Status</th>
							<th>Invoice</th>
						</tr>
					</thead>
					<tbody>						
						<tr>
							<td align="center">${ requestPayment.id }</td>
							<td align="center"><span class="price-higlight">${ requestPayment.amount }</span></td>
							<c:choose>
							    <c:when test="${ requestPayment.status=='0' }">
		       						<td align="center">Unpaid</td>
							    </c:when>
							    <c:when test="${ requestPayment.status=='1' }">
		       						<td align="center">Paid</td>
							    </c:when>					    						    					    						        
							    <c:otherwise>
		       						<td align="center">-??-</td>
							    </c:otherwise>
							</c:choose>
							<td align="center"><a href="?" class="btn btn-info">Download</a></td>
						</tr>						
					</tbody>
				</table>
			</div>
			</div>			
	  			
	  		<%-- Chat: --%>		
	  					
  			<br />[[ CHAT WILL BE THERE ]]<br />
  			${requestChat}
	  			
	  		<%-- End of Chat: --%>		
	  		</c:otherwise>
		</c:choose>
    </c:otherwise>
</c:choose>
<%@ include file="../commons/footer.jspf" %>	