<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="Willie: Create">
	<div class="col-md-10 col-xs-12 col-centered" style="background-color: white;">
	<c:if test="${param.error}">
		Invalid username or password
	</c:if>
		<form:form method="post" modelAttribute="user" cssClass="input-form">
			<div class="form-group">
				<label for="name">Username</label>
				<form:input path="username" type="text" id="username" placeholder="Username" cssClass="form-control"/>
				<form:errors path="username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<form:input path="password" type="password" id="password" placeholder="Password" cssClass="form-control"/>
				<form:errors path="password" />
			</div>
			<div class="form-group">
				<label for="password">Retype password</label>
				<form:input path="rePassword" type="password" id="rePassword" placeholder="Retype password" cssClass="form-control"/>
				<form:errors path="rePassword" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success btn-block">
					<i class="fa fa-plus"></i> Login
				</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
	</div>
</t:layout>