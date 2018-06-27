<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
	id="mainNav">
	<a class="navbar-brand" href="<c:url value="/"/>"><img
		src="<c:url value="/resources/img/logo-total.png"/>"></a>
	
	<div class="collapse navbar-collapse" id="navbarResponsive">
		
		
		<ul class="navbar-nav ml-auto">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown"
				href="#" data-toggle="dropdown"> Registers
			</a>
				<div class="dropdown-menu">					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/household.html"/>"> <strong> Household</strong> 
						
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/elco.html"/>"> <strong>Elco</strong>
						
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/mother.html"/>"> <strong>Mother</strong> 
						
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item small" href="<c:url value="/child.html"/>"><strong>Child</strong> </a>
				</div>
			</li>
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="reportDropdown"
				href="#" data-toggle="dropdown"> Reports
			</a>
				<div class="dropdown-menu">					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/formWiseReport.html"/>"> <strong> Form Wise Report Status</strong> 
						
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/providerWiseReport.html"/>"> <strong>Provider Wise Report Status</strong>
					</a>					
					
				</div>
			</li>
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="exportDropdown"
				href="#" data-toggle="dropdown"> Exports
			</a>
				<div class="dropdown-menu">					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/export.html"/>"> <strong> Export CSV</strong> 
						
					</a>
										
					
				</div>
			</li>
			
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="fwaMonitoringDropdown"
				href="#" data-toggle="dropdown"> FWA Monitoring
			</a>
				<div class="dropdown-menu">					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/fwa/anc/monitoring.html"/>"> <strong> ANC</strong> 
						
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/fwa/pnc/monitoring.html"/>"> <strong>PNC</strong>
					</a>
					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/fwa/encc/monitoring.html"/>"> <strong>ENCC</strong>
					</a>					
					
				</div>
			</li>
			
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="roleDropdown"
				href="#" data-toggle="dropdown"> Role
			</a>
				<div class="dropdown-menu">	
				
				<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/role/add.html"/>"> <strong>Add</strong>
					</a>				
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/role.html"/>"> <strong> List</strong> 
						
					</a>
				</div>
			</li>
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle mr-lg-2" id="userDropdown"
				href="#" data-toggle="dropdown"> User
			</a>
				<div class="dropdown-menu">	
				
				<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/user/add.html"/>"> <strong>Add</strong>
					</a>				
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/user.html"/>"> <strong> List</strong> 
						
					</a>
				</div>
			</li>
			
			
			
			
			
			
			<li class="nav-item"><a class="nav-link" data-toggle="modal"
				data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Logout
			</a></li>
		</ul>
	</div>
</nav>

