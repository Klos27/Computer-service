<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Browse requests<i class="fas fa-angle-right ml-3"></i></h1>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%=  
	request.getAttribute("userRequests")	
%>

<%@ include file="../commons/footer.jspf" %>	