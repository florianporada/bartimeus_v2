<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="{{ 'create_title' | translate }}">
	<div class="col-md-10 col-xs-12 col-centered" style="background-color: white;">
		<c:if test="${param.error}">
			{{ 'login_error' | translate }}
		</c:if>
		<form:form method="post" modelAttribute="user" cssClass="input-form">
			<div class="form-group">
				<label for="name">{{ 'username' | translate }}</label>
				<form:input path="username" type="text" id="username" placeholder="{{ 'username' | translate }}" cssClass="form-control"/>
				<form:errors path="username" />
			</div>
			<div class="form-group">
				<label for="password">{{ 'password' | translate }}</label>
				<form:input path="password" type="password" id="password" placeholder="{{ 'password' | translate }}" cssClass="form-control"/>
				<form:errors path="password" />
			</div>
			<div class="form-group">
				<label for="rePassword">{{ 're_password' | translate }}</label>
				<form:input path="rePassword" type="password" id="rePassword" placeholder="{{ 're_password' | translate }}" cssClass="form-control"/>
				<form:errors path="rePassword" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success btn-block">
					<i class="fa fa-plus"></i> {{ 'create_button' | translate }}
				</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form:form>
	</div>
</t:layout>