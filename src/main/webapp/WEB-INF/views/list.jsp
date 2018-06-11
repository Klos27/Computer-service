<%@ include file="../commons/header.jspf" %>

<h1 class="text-center mb-4"><i class="fas fa-angle-left mr-3"></i>Users list<i class="fas fa-angle-right ml-3"></i></h1>

<div class="row mb-4">
<div class="col-md-10 offset-md-1">
	<div class="dropdown show float-right ">
	  <a class="btn btn-danger dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    Filter users
	  </a>
	
	  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
	    <a class="dropdown-item" href="?role=">Everybody</a>
	    <a class="dropdown-item" href="?role=0">User</a>
	    <a class="dropdown-item" href="?role=3">Workman</a>
	    <a class="dropdown-item" href="?role=2">Foreman</a>
	  </div>
	</div>
</div>
</div>

<div class="row">

	<div class="col-md-10 offset-md-1">
		<table class="table">
			<thead>
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>
				<th>Role</th>
			</tr>
			</thead>
			<tbody>

				<c:forEach items="${users}" var="user" >
					<tr>
						<td>${ user.id } </td>
						<td>${ user.first_name }</td>
						<td>${ user.last_name }</td>
						<td>${ user.email }</td>
						<td>
						    <select class="form-control select-role">
						      <option value="0-${user.id}" ${user.role == 0 ? 'selected="selected"' : ''}>User</option>
						      <option value="3-${user.id}" ${user.role == 3 ? 'selected="selected"' : ''}>Worker</option>
						      <option value="2-${user.id}" ${user.role == 2 ? 'selected="selected"' : ''}>Foreman</option>
						    </select>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>

</div>

<%@ include file="../commons/footer.jspf" %>	