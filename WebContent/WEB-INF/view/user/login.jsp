<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="Bartiméus: Login">
	<div class="col-md-10 col-xs-12 col-centered" style="background-color: white;">
		<c:if test="${not empty error}">
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
				<p>Don't have an account? <a href="../create/">Create one now!</a></p>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success btn-block">
					Login
				</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
	</div>
</t:layout>