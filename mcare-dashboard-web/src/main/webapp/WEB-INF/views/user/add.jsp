<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="org.mcare.common.util.CheckboxHelperUtil"%>

<%@page import="java.util.List"%>
<%@page import="org.mcare.acl.entity.Role"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add user information</title>

<jsp:include page="/WEB-INF/views/css.jsp"/>
</head>
<c:url var="saveUrl" value="/user/add.html" />
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<jsp:include page="/WEB-INF/views/navbar.jsp"/>

	<div class="content-wrapper">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Register an Account</div>
      <div class="card-body">
       <form:form method="POST" action="${saveUrl}" modelAttribute="account">
          <div class="form-group">
          
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputName">User Name</label>                
                <form:input path="username" class="form-control" required="required" aria-describedby="nameHelp" placeholder="Enter first name" />
                ${unigue}
              </div>
              <div class="col-md-6">
                <label for="exampleInputLastName">Email</label>                
                <form:input path="email" class="form-control" required="required" aria-describedby="nameHelp" placeholder="Enter last name" />
                ${unigue}
              </div>
            </div>
            
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputName">First name</label>
                <form:input path="firstName" class="form-control" aria-describedby="nameHelp" placeholder="Enter first name"/>
              </div>
              <div class="col-md-6">
                <label for="exampleInputLastName">Last name</label>
                <form:input path="lastName"  class="form-control" aria-describedby="nameHelp" placeholder="Enter last name" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPassword1">Password</label>                
                <form:password path="password" class="form-control" placeholder="Password" required="required"/>
              </div>
              <div class="col-md-6">
                <label for="exampleConfirmPassword">Confirm password</label>
                <form:password path="retypePassword"  placeholder="Confirm password" class="form-control" required="required" />
              </div>
            </div>
            ${passwordNotMatch}
          </div>
          <div class="form-group">
              <div class="form-check">
                 <%   
                  List<Role>  roles = (List<Role>)session.getAttribute("roles"); 
                  int[]  selectedRoles = (int[]) session.getAttribute("selectedRoles");
                  for(Role role:roles){                     
                  %>                      
                  <form:checkbox class="checkBoxClass form-check-input" path="roles"  value="<%=role.getId()%>" checked="<%=CheckboxHelperUtil.checkCheckedBox(selectedRoles,role.getId())%>" />
                   <label class="form-check-label" for="defaultCheck1"> <%=role.getName()%> </label>
                  
                  <% 
                  }
                  %>
              </div>
          </div>
          <input type="submit" value="Save" class="btn btn-primary btn-block" />
          
       </form:form>
        
      </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <jsp:include page="/WEB-INF/views/footer.jsp"/>
  </div>
</body>
</html>