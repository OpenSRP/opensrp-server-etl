<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title> Schedule Monitoring</title>

<jsp:include page="/WEB-INF/views/css.jsp" />
</head>

<c:url var="saveUrl" value="/role/add" />

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<jsp:include page="/WEB-INF/views/navbar.jsp" />

	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Example DataTables Card-->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Schedule Monitoring
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable">
							<thead>
								<tr>
									<th>FullName</th>
									<th>User Name</th>
									<th>Email</th>
									<th>Role</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>FullName</th>
									<th>User Name</th>
									<th>Email</th>
									<th>Role</th>
									<th>Actions</th>
								</tr>
							</tfoot>
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer small text-muted"></div>
			</div>
		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<jsp:include page="/WEB-INF/views/footer.jsp" />
	</div>
</body>
</html>