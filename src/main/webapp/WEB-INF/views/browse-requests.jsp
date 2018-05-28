<%@ include file="../commons/header.jspf" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty userNotification}">
<%-- Notify user about errrors --%>
<div class="user-request-notification"> ${userNotification} </div>
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
							<c:choose>
							    <c:when test="${not empty requestPayment}">
		       						<td align="center"><a target="_blank" href="/user/download-invoice?request=${ requestDetails.id }&invoice=${ requestPayment.id }" class="btn btn-info">Download</a></td>
							    </c:when>			    						    					    						        
							    <c:otherwise>
		       						<td align="center"></td>
							    </c:otherwise>
							</c:choose>
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
							<th>Creation date</th>
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
		       						<td align="center"></td>
							    </c:otherwise>
							</c:choose>
							<td align="center">${ requestPayment.creation_date }</td>
							<c:choose>
							    <c:when test="${not empty requestPayment}">
		       						<td align="center"><a target="_blank" href="/user/download-invoice?request=${ requestDetails.id }&invoice=${ requestPayment.id }" class="btn btn-info">Download</a></td>
							    </c:when>			    						    					    						        
							    <c:otherwise>
		       						<td align="center"></td>
							    </c:otherwise>
							</c:choose>
							</tr>						
					</tbody>
				</table>
			</div>
			</div>			
	  		<c:if test="${not empty requestPayment}">
				<%-- Print account info --%>
				
				<div class="row">
				<div class="col-md-4 offset-md-4">
					<table class="table">
						<thead align="center" valign="middle">
						<tr>
							<th>You can pay to our account:</th>
						</tr>
						</thead>
						<tbody>			
							<tr><td>ISI Bank 11 2222 3333 4444 5555 6666 7777</td></tr>
							<tr>
								<td>
									PPPM Computer Service<br />
									ul. Warszawska 24<br />
									31-155 Kraków
								</td>
							</tr>
							<tr><td>Amount: ${ requestPayment.amount } zł</td></tr>
							<tr><td>Title: Service request ${ requestDetails.id } invoice ${ requestPayment.id }</td></tr>
						</tbody>
					</table>
				</div>
				</div>						
			</c:if>
	  			
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
    </c:otherwise>
</c:choose>
<%@ include file="../commons/footer.jspf" %>	